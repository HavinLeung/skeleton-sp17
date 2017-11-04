package db;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Database {
    private Parser parser;
    private Map<String, Table> tableMap;

    public Database() {
        parser = new Parser();
        tableMap = new HashMap<>();
    }

    /**
     * This method takes a user query and if it passes a preliminary parser check,
     * it goes to call command method
     *
     * @param query the user query
     * @return the success/failure message to be printed
     */
    public String transact(String query) {
        // get query
        if (query.trim().isEmpty()) {
            return "";
        }
        return (parser.parse(query));
    }

    /**
     * This method takes in a string array representing the command, and calls the command
     *
     * @param cmd the command
     * @return the success/failure message to be printed
     */
    private String callCommand(String[] cmd) {
        switch (cmd[0]) {
            case "create":
                break;
            case "load":
                break;
            case "store":
                break;
            case "drop":
                break;
            case "insert":
                break;
            case "print":
                break;
            case "select":
                break;
        }
        return ""; //should never happen
    }

    /**
     * @param name name of the table
     * @param cols array of columns
     * @return success/failure message
     */
    private String createNewTable(String name, String[] cols) {
        //TODO:

        /* Check if the table already exists */
        //check against all files in directory
        File curDir = new File(".");
        File[] allFiles = curDir.listFiles();
        for (File f : allFiles) {
            if (f.isFile()) {
                if (f.getName().equals(name + ".tbl")) {
                    return String.format("Error: Table %s already exists!\n", name);
                }
            }
        }
        //check against all loaded files
        for (String key : tableMap.keySet()) {
            if (key.equals(name)) {
                return String.format("Error: Table %s already exists!\n", name);
            }
        }
        /* Check if the column types are valid */
        for (String col : cols) {
            String type = col.split("\\s+")[1];
            // invalid type
            if (!(type.equals("int") || type.equals("float") || type.equals("string"))) {
                return String.format("Error: column %s is of unknown type: %s\n", col, type);
            }
        }
        tableMap.put(name, new Table(cols));

        return "";
    }

    /**
     * @param name   name of the table
     * @param exprs  expressions
     * @param tables table names
     * @param conds  conditions
     * @return success/failure message
     */
    private String createSelectTable(String name, String exprs, String tables, String conds) {
        //TODO:
        return "";
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String loadTable(String expr) {
        //TODO
        return String.format("Malformed load: %s\n", expr);
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String storeTable(String expr) {
        //TODO
        return String.format("Malformed store: %s\n", expr);
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String dropTable(String expr) {
        //TODO
        return String.format("Malformed drop: %s\n", expr);
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String insertIntoTable(String expr) {
        //TODO
        return String.format("Malformed insert: %s\n", expr);
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String printTable(String expr) {
        //TODO
        return String.format("Malformed print: %s\n", expr);
    }

    /**
     * @param expr expression
     * @return success/failure message
     */
    private String select(String expr) {
        //TODO
        return String.format("Malformed select: %s\n", expr);
    }

    /**
     * private class to help with parsing queries
     */
    private class Parser {
        // Various common constructs, simplifies parsing.
        private final String
                REST = "\\s*(.*)\\s*",
                COMMA = "\\s*,\\s*",
                AND = "\\s+and\\s+";

        // Stage 1 syntax, contains the command name.
        private final Pattern
                CREATE_CMD = Pattern.compile("create table " + REST),
                LOAD_CMD = Pattern.compile("load " + REST),
                STORE_CMD = Pattern.compile("store " + REST),
                DROP_CMD = Pattern.compile("drop table " + REST),
                INSERT_CMD = Pattern.compile("insert into " + REST),
                PRINT_CMD = Pattern.compile("print " + REST),
                SELECT_CMD = Pattern.compile("select " + REST);

        // Stage 2 syntax, contains the clauses of commands.
        private final Pattern
                CREATE_NEW = Pattern.compile("(\\S+)\\s+\\(\\s*(\\S+\\s+\\S+\\s*" +
                "(?:,\\s*\\S+\\s+\\S+\\s*)*)\\)"),
                SELECT_CLS = Pattern.compile("([^,]+?(?:,[^,]+?)*)\\s+from\\s+" +
                        "(\\S+\\s*(?:,\\s*\\S+\\s*)*)(?:\\s+where\\s+" +
                        "([\\w\\s+\\-*/'<>=!.]+?(?:\\s+and\\s+" +
                        "[\\w\\s+\\-*/'<>=!.]+?)*))?"),
                CREATE_SEL = Pattern.compile("(\\S+)\\s+as select\\s+" +
                        SELECT_CLS.pattern()),
                INSERT_CLS = Pattern.compile("(\\S+)\\s+values\\s+(.+?" +
                        "\\s*(?:,\\s*.+?\\s*)*)");

        private String parse(String query) {
            Matcher m;
            if ((m = CREATE_CMD.matcher(query)).matches()) {
                return createTable(m.group(1));
            } else if ((m = LOAD_CMD.matcher(query)).matches()) {
                return loadTable(m.group(1));
            } else if ((m = STORE_CMD.matcher(query)).matches()) {
                return storeTable(m.group(1));
            } else if ((m = DROP_CMD.matcher(query)).matches()) {
                return dropTable(m.group(1));
            } else if ((m = INSERT_CMD.matcher(query)).matches()) {
                return insertIntoTable(m.group(1));
            } else if ((m = PRINT_CMD.matcher(query)).matches()) {
                return printTable(m.group(1));
            } else if ((m = SELECT_CMD.matcher(query)).matches()) {
                return select(m.group(1));
            }
            return String.format("Malformed query: %s\n", query);
        }

        /**
         * @param expr expression
         * @return success/failure message
         */
        private String createTable(String expr) {
            //TODO
            Matcher m;
            if ((m = CREATE_NEW.matcher(expr)).matches()) {
                return createNewTable(m.group(1), m.group(2).split(COMMA));
            } else if ((m = CREATE_SEL.matcher(expr)).matches()) {
                return createSelectTable(m.group(1), m.group(2), m.group(3), m.group(4));
            }
            return String.format("Malformed create: %s\n", expr);
        }
    }
}
