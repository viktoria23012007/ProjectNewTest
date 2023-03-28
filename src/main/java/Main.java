import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // объект сканнера, умеющий читать что ввёл пользователя
        var scanner = new Scanner(System.in);

        // бесконечно повторяем код в {}
        while (true) {
            System.out.println("Введи поисковую строку:");

            // считываем что он ввёл, перводим в маленькие буквы
            var input = scanner.nextLine();
            var inputSmall = input.toLowerCase();

            // пробегаемся по файлам в папке pdfs
            File dir = new File("pdfs");
            for (File pdfFile : dir.listFiles()) {

                // считываем текст из пдфки, переводим в маленькие буквы
                var reader = new PdfReader(pdfFile);
                var doc = new PdfDocument(reader);
                var page = doc.getPage(1);
                var text = PdfTextExtractor.getTextFromPage(page);
                var textSmall = text.toLowerCase();

                // если нашли слово в тексте, пишем название файла
                if (textSmall.contains(inputSmall)) {
                    System.out.println("Нашли в " + pdfFile.getName());

                    System.out.println("Нажмите Enter для продолжения поиска");
                    String answer = scanner.nextLine();
                    if (!answer.isEmpty()) {
                        break;
                    }
                }
            }
        }


    }
}
