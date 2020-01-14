package excel;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class ExceptionMappingGenerator {
    private static final String FILE_PATH = "/Users/unknown7/Documents/exception_mapping.xlsx";
    private static final Collection SHEETS = Lists.newArrayList(
            "ykc",
            "loc",
            "lmc",
            "lsc"
    );
    private static final int START_ROW_NUM = 2;
    private static final int ORIGINAL_EXCEPTION_CODE_POSITION = 1;
    private static final int ORIGINAL_EXCEPTION_MESSAGE_POSITION = 2;
    private static final int TRANSFORMED_EXCEPTION_MESSAGE_POSITION = 4;
    private String a = "INSERT INTO db_gmcf_gwc.t_gwc_exception_mapping (exception, original_code, original_message, transformed_default_code, transformed_default_message, module, dynamic_binding_switch, dynamic_binding, hidden, create_time, update_time, del_flag) VALUES ('ConstraintViolationException', null, '参数错误, 不能为空', '-1', '系统繁忙，请稍后再试', 'ykc', 1, '[{\"key\": \"/trialInterfaceWithConfig\", \"code\": \"01103010001\", \"message\": \"系统繁忙，请稍后再试【01103010001】\"}]', 0, '2019-11-15 10:45:59', '2019-11-15 10:45:59', 0);";
    private static final String TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Workbook workbook = ExcelUtil.getWorkbook(FILE_PATH);
        int numberOfSheets = workbook.getNumberOfSheets();
        int total = 0;
        for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null || !SHEETS.contains(sheet.getSheetName())) {
                continue;
            }
            int rowNos = sheet.getLastRowNum();// 得到excel的总记录条数
            for (int j = START_ROW_NUM; j <= rowNos; j++) {// 遍历行
                Row row = sheet.getRow(j);
                if (row != null) {
                    String originalCode = row.getCell(ORIGINAL_EXCEPTION_CODE_POSITION).getStringCellValue();
                    String originalMessage = row.getCell(ORIGINAL_EXCEPTION_MESSAGE_POSITION).getStringCellValue();
                    String transformedMessage = row.getCell(TRANSFORMED_EXCEPTION_MESSAGE_POSITION).getStringCellValue();
                    if (StringUtils.isNotEmpty(transformedMessage)) {
                        final String EXCEPTION = sheet.getSheetName().substring(0, 1).toUpperCase() + sheet.getSheetName().substring(1) + "BusinessException";
                        final String ORIGINAL_CODE = originalCode;
                        final String ORIGINAL_MESSAGE = originalMessage;
                        final String TRANSFORMED_DEFAULT_CODE = null;
                        final String TRANSFORMED_DEFAULT_MESSAGE = StringUtils.isBlank(transformedMessage) ? null : transformedMessage;
                        final String MODULE = sheet.getSheetName();
                        final Integer DYNAMIC_BINDING_SWITCH = 1;
                        final String DYNAMIC_BINDING = "[]";
                        final Integer HIDDEN = 0;
                        final String CREATE_TIME = TIME;
                        final String UPDATE_TIME = TIME;
                        final Integer DEL_FLAG = 0;

                        StringBuilder builder = new StringBuilder("INSERT INTO db_gmcf_gwc.t_gwc_exception_mapping (exception, original_code, original_message, transformed_default_code, transformed_default_message, module, dynamic_binding_switch, dynamic_binding, hidden, create_time, update_time, del_flag) VALUES (");
                        builder.append("'" + EXCEPTION + "'")
                                .append(", ")
                                .append("'" + ORIGINAL_CODE + "'")
                                .append(", ")
                                .append("'" + ORIGINAL_MESSAGE + "'")
                                .append(", ")
                                .append(TRANSFORMED_DEFAULT_CODE)
                                .append(", ")
                                .append("'" + TRANSFORMED_DEFAULT_MESSAGE + "'")
                                .append(", ")
                                .append("'" + MODULE + "'")
                                .append(", ")
                                .append(DYNAMIC_BINDING_SWITCH)
                                .append(", ")
                                .append("'" + DYNAMIC_BINDING + "'")
                                .append(", ")
                                .append(HIDDEN)
                                .append(", ")
                                .append("'" + CREATE_TIME + "'")
                                .append(", ")
                                .append("'" + UPDATE_TIME + "'")
                                .append(", ")
                                .append(DEL_FLAG)
                                .append(");");

                        System.err.println(builder);
                        total++;
                    }
                }
            }
        }
        System.err.println("total: " + total);
    }
}
