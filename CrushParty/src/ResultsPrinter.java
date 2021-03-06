import java.io.PrintWriter;
import java.util.List;

public class ResultsPrinter {

    private static int id = 0;

    public static void printResults (Student person) {

        PrintWriter document = null;
        try {
            document = new PrintWriter("./results/"
                    + (id++) + " " + person.getName() + ".tex", "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw(new RuntimeException(e));
        }

        document.println("");
        document.println("\\documentclass[a4paper]{article}");
        document.println("");
        document.println("\\usepackage[english]{babel}");
        document.println("\\usepackage[utf8]{inputenc}");
        document.println("\\usepackage{parskip}");
        document.println("");
        document.println("\\usepackage{geometry}");
        document.println("\\geometry{");
        document.println("    tmargin   = 1.5 in,");
        document.println("    lmargin   = 1.00 in,");
        document.println("    rmargin   = 1.00 in,");
        document.println("    bmargin   = 1.00 in,");
        document.println("    headheight  = 0.50 in,");
        document.println("    headsep   = 0.25 in");
        document.println("}");
        document.println("");
        document.println("\\usepackage{fancyhdr}");
        document.println("\\pagestyle{fancy}");
        document.println("\\fancyhf{}");
        document.println("\\fancyhead[L]{Crush Party 2015}");
        document.println("\\fancyhead[R]{Results For: " + person.getName() + " \\\\");
        document.println("(" + person.getGender() + ", " + person.getYear() +
                ", " + person.getCollege() + ", " + person.getMajor() + ")}");
        document.println("");
        document.println("\\begin{document}");
        document.println("");
        document.println("\\section*{Best Results}");
        document.println("");
        writeOneTable(document, person, "List 1", 0);
        writeOneTable(document, person, "List 2", 2);
        writeOneTable(document, person, "List 3", 4);
        document.println("\\newpage");
        document.println("\\section*{Worst Results}");
        document.println("");
        writeOneTable(document, person, "List 1", 1);
        writeOneTable(document, person, "List 2", 3);
        writeOneTable(document, person, "List 3", 5);
        document.println("\\end{document}");

        document.close();

    }

    private static void writeOneTable (PrintWriter document, Student person,
                                  String listName, int index) {

        List<Student> matches = person.getMatchesForList(index);

        document.println("\\subsection*{" + listName + "}");
        document.println("");
        document.println(person.getDescriptionForList(index) + " \\\\");
        document.println("");
        document.println("\\begin{tabular}{| l | l | l | l | l | l |}");
        document.println("    \\hline");
        document.println("    Name & Match & Gender & Year & College & Major \\\\");
        document.println("    \\hline");
        for (int i = 0; i < 10; i++) {
            Student match = matches.get(i);
            String gen;
            if (match.getGender() != null && match.getGender() == Gender.NOTHING) {
                gen = "";
            } else if (match.getGender() != null) {
                String genA = match.getGender().toString().substring(0, 1);
                String genB = match.getGender().toString().substring(1).toLowerCase();
                gen = genA + genB;
            } else {
                gen = "";
            }

            document.println("    " + i + ". " + match.getName() + " & "
                    + Math.round(person.getPercentagesForList(index).get(i) * 100) + "\\% & "
                    + gen + " & " + match.getYear() + " & "
                    + match.getCollege() + " & " + match.getMajor() + " \\\\");
        }
        document.println("    \\hline");
        document.println("\\end{tabular}");
        document.println("");

    }

}
