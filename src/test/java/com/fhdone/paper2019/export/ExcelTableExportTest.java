package com.fhdone.paper2019.export;


import com.fhdone.paper2019.BaseTest;
import com.fhdone.paper2019.model.Student;
import com.fhdone.paper2019.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.util.concurrent.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Ignore
public class ExcelTableExportTest extends BaseTest {

    private Logger logger = LogManager.getLogger(ExcelTableExportTest.class);
    private final int PAGE_SIZE = 5;
    private final int THREAD_SIZE = 3;

    @Autowired
    StudentService studentService;

    @Test
    @Ignore
    public void createPoiExcelTableExport() throws Exception {
        TableExport export = TableExportFactory.createPoiExcelTableExport();
        queryDataByMutiThread(export,1);
        export.export(new FileOutputStream(new File("学生信息Poi.xls")));

        export = TableExportFactory.createPoiExcelTableExport("xlsx");
        queryDataByMutiThread(export,1);
        export.export(new FileOutputStream(new File("学生信息Poi.xlsx")));

    }

    @Test
    @Ignore
    public void createPoiExcelTableExport2() throws Exception {
        TableExport export = TableExportFactory.createPoiExcelTableExport();
        queryDataByMutiThread(export,2);
        export.export(new FileOutputStream(new File("学生信息Poi2.xls")));

        export = TableExportFactory.createPoiExcelTableExport();
        queryDataByMutiThread(export,2);
        export.export(new FileOutputStream(new File("学生信息Poi2.xlsx")));
    }


    /**
     * 多线程下载
     */
    public void queryDataByMutiThread(TableExport export , int  type ) throws Exception {

        Object[] titleRow = new Object[]{"序号", "名字", "地址", new Date()};
        export.setSheetName("学生信息");
        export.defineTitle(titleRow, new DataFormat[]{new DataFormat(DataFormatFactory.TITLT)});

        Long count = studentService.countStudents();
        long pageSum = (long) Math.ceil((double) count / PAGE_SIZE);
        logger.debug("PageSum:" + pageSum);
        if(type==1) {
            exportLcs(export, pageSum);
        }else{
            exportCs(export, pageSum);
        }
    }

    /**
     * process by ListeningExecutorService
     */
    private void exportLcs(TableExport export, long pageSum) throws InterruptedException, ExecutionException {

        AtomicInteger token = new AtomicInteger(1);
        ExecutorService es = Executors.newFixedThreadPool(THREAD_SIZE);
        ListeningExecutorService service = MoreExecutors.listeningDecorator(es);
        CountDownLatch pageCountDownLatch = new CountDownLatch((int) pageSum);
        AtomicInteger rowNum = new AtomicInteger(0);

        ListeningExecutorService esEmpty = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        for (int pageNum = 1; pageNum <= pageSum; pageNum++) {
            StudentQueryCallable sqc = new StudentQueryCallable(pageNum, token,pageCountDownLatch);
            ListenableFuture<List<Student>> listenableFuture = service.submit(sqc);
            Futures.addCallback(listenableFuture, new FutureCallback<List<Student>>() {

                public void onSuccess(List<Student> result) {
                    putStudentInfoToRow(export, result, rowNum);
                    sqc.afterProcess();
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                    pageCountDownLatch.countDown();
                }
            },esEmpty);
        }
        pageCountDownLatch.await();
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    /**
     * process by CompletionService
     */
    private void exportCs(TableExport export, long pageSum) throws InterruptedException, ExecutionException {

        AtomicInteger token = new AtomicInteger(1);
        ExecutorService es = Executors.newFixedThreadPool(THREAD_SIZE);
        CompletionService<List<Student>> cs = new ExecutorCompletionService<List<Student>>(es);
        AtomicInteger rowNum = new AtomicInteger(0);

        for (int pageNum = 1; pageNum <= pageSum; pageNum++) {
            cs.submit(new StudentQueryCallable(pageNum, token));
        }

        for (int pageNum = 1; pageNum <= pageSum; pageNum++) {
            List<Student> listStu = cs.take().get();
            putStudentInfoToRow(export, listStu, rowNum);
            token.incrementAndGet();
        }
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }


    /**
     * 学生信息放入row
     */
    private static void putStudentInfoToRow(TableExport export, List<Student> listStu, AtomicInteger rowNum) {
        for (Student stu : listStu) {
            Object[] rowData;
            if (stu.getId() != null) {
                rowData = new Object[]{stu.getId().doubleValue() / 10, stu.getStuName(), stu.getStuAdd()};
            } else {
                rowData = new Object[]{stu.getId(), stu.getStuName(), stu.getStuAdd()};
            }
            if (rowNum.get() % 2 == 0) {
                export.addRow(rowData, new DataFormat[]{new DataFormat(DataFormatFactory.NORMAL)});
            } else {
                export.addRow(rowData, new DataFormat[]{new DataFormat(DataFormatFactory.LARGE)});
            }
            export.addRow("备份", rowData);
            rowNum.incrementAndGet();
        }
    }

    /**
     * 学生信息查询
     */
    private class StudentQueryCallable extends DataQueryCallable<List<Student>> {

        private CountDownLatch pageCountDownLatch;

        public StudentQueryCallable(Integer pageNum, AtomicInteger token) {
            super();
            this.pageNum = pageNum;
            this.token = token;
        }

        public StudentQueryCallable(Integer pageNum, AtomicInteger token,CountDownLatch pageCountDownLatch) {
            super();
            this.pageNum = pageNum;
            this.token = token;
            this.pageCountDownLatch = pageCountDownLatch;
        }

        @Override
        public List<Student> queryPageData() {
            Page<Student> page = PageHelper.startPage(pageNum, PAGE_SIZE).doSelectPage(()-> studentService.getAllStudents());
            for(Student stu:page.getResult()) {
                logger.info("pageNum:{},{}" ,this.pageNum , stu.toString()  );
            }
            return page.getResult();
        }

        @Override
        public void afterProcess() {
            token.incrementAndGet();
            if(pageCountDownLatch!=null) {
                pageCountDownLatch.countDown();
            }
        }

    }

}
