package edufarm.excelView;

import edufarm.model.Reservation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * {@link edufarm.model.Reservation} 객체에 대한 엑셀(MS-Excel) 뷰.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Reservation
 */
public class ReservationExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Reservation> reservationList = (List<Reservation>) model.get("reservationList");
        HSSFCellStyle dateStyle = workbook.createCellStyle();
        HSSFCellStyle datetimeStyle = workbook.createCellStyle();
        DataFormat df = workbook.createDataFormat();
        dateStyle.setDataFormat(df.getFormat("yyyy-MM-dd"));
        datetimeStyle.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm"));

        HSSFSheet sheet = workbook.createSheet();

        HSSFRow row0 = sheet.createRow(0);
        String[] header = {"#", "예약일", "제목", "어른", "아이", "파트", "이름", "이메일", "휴대폰 번호", "작성일", "진행상황"};
        for (int i = 0; i < header.length; ++i)
            addStringCell(row0, i, header[i]);

        for (int i = 0; i < reservationList.size(); ++i) {
            Reservation reservation = reservationList.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            addIntCell(row, 0, reservation.getId());
            addDateCell(row, 1, reservation.getDate(), dateStyle);
            addStringCell(row, 2, reservation.getTitle());
            addIntCell(row, 3, reservation.getAdult());
            addIntCell(row, 4, reservation.getChild());
            addIntCell(row, 5, reservation.getPart());
            addStringCell(row, 6, reservation.getUser().getDisplayName());
            addStringCell(row, 7, reservation.getUser().getEmail());
            addStringCell(row, 8, reservation.getUser().getPhone());
            addDateCell(row, 9, reservation.getPublished(), datetimeStyle);
            addStringCell(row, 10, reservation.getReservationState().toString());
        }

        for (int i = 0; i < header.length; ++i)
            sheet.autoSizeColumn(i);
    }

    private HSSFCell addStringCell(HSSFRow row, int index, String value) {
        HSSFCell cell = row.createCell((short) index);
        cell.setCellValue(new HSSFRichTextString(value));
        return cell;
    }


    private HSSFCell addIntCell(HSSFRow row, int index, int value) {
        HSSFCell cell = row.createCell((short) index);
        cell.setCellValue(value);
        return cell;
    }

    private HSSFCell addDateCell(HSSFRow row, int index, Date date,
                                 HSSFCellStyle dateStyle) {
        HSSFCell cell = row.createCell((short) index);
        cell.setCellValue(date);
        cell.setCellStyle(dateStyle);
        return cell;
    }
}


