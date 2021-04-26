package com.jxx.excel.easyexcel;

/**
 * @author Strange
 * @ClassName TitleSheetWriteHandler.java
 * @Description TODO
 * @createTime 2021年04月26日 15:58:00
 */
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class TitleSheetWriteHandler implements SheetWriteHandler {
    private String title;
    private int lastCol;
    public TitleSheetWriteHandler(String title,int lastCol){
        this.title = title;
        this.lastCol = lastCol;
    }
    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Workbook workbook = writeWorkbookHolder.getWorkbook();
        Sheet sheet = workbook.getSheetAt(0);
        //设置标题
        Row row = sheet.createRow(0);
        row.setHeight((short) 800);
        Cell cell = row.createCell(0);
        cell.setCellValue(title);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight((short) 400);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
        sheet.addMergedRegionUnsafe(new CellRangeAddress(0, 0, 0, lastCol));
    }
}
