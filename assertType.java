package evrika;

public class assertType {
    public assertType(String paramName, Object paramValue, String type) {
        String testDateFormat;
        String paramType = "class java.lang." + type;
        if (type == "Date") {
            paramType = "class java.lang.String";
        }
        if (type == "Array") {
            paramType = "class org.json.simple.JSONArray";
        }
        if (paramValue == null) {
            System.out.println("Параметр " + paramName + " не приходит в методе");
        }

        else {
            String testType = String.valueOf(paramValue.getClass());

            if (testType.equals(paramType)) {
                System.out.println(paramName + " - ок");
                if (type == "Date") {
                    testDateFormat = String.valueOf(paramValue);
                    if (testDateFormat.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
                        System.out.println("Формат даты - ок");
                    } else {
                        System.out.println("Формат даты - не ок");
                    }
                }
            } else {
                System.out.println("Параметр - " + paramName + " Ожидали тип - " + paramType + " Факт - " + testType);
            }
        }
    }
}