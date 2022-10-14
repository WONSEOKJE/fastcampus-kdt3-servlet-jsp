package me.others.day14.practice01;

import java.util.Iterator;
import java.util.Map;
import java.util.function.Predicate;

public class StudentDao {
    private static StudentDao studentDao;
    private Map<String, Student> map = DataBase.getInstance().getTableList().get(0).getData();

    public static StudentDao getInstance() {
        if (studentDao == null) {
            studentDao = new StudentDao();
        }
        return studentDao;
    }


    public Student select(String sNum) {
        Students students = new Students();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String num = iterator.next();
            Student student = map.get(sNum);

            if (num != null) {
                if (num.equals(sNum)) {
                    students.getStudents().put(num, student);
                    return students.getStudents().get(num);
                }
            }
        }
        return null;
    }


    public Students select(Predicate<Student> condition) {
        Students students = new Students();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String num = iterator.next();
            Student student = map.get(num);

            if (condition != null) {
                if (condition.test(student)) {
                    students.getStudents().put(num, student);
                }
            }
        }

        return students;
    }


    public Students select() {
        return new Students(map);
    }


    public Student insert(String key, Student student) {
        if (map.containsKey(key)) {
            System.out.println("duplicated key.");
            return null;
        }
        map.put(key, student);
        return student;
    }

    public int insert(Students students) {
        int count = 0;
        Iterator<String> iterator = students.getStudents().keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Student value = students.getStudents().get(key);

            if (map.containsKey(key)) {
                System.out.println("duplicated key.");
                break;
            }
            map.put(key, value);
            count++;
        }
        return count;
    }

    public int update(Predicate<Student> condition, Column column, Object value) {
        int count = 0;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String num = iterator.next();
            Student student = map.get(num);

            if (condition != null) {
                if (condition.test(student)) {
                    if (column != null && column.getColumnName() != null) {
                        if (column.getColumnName().equals("sId")) {
                            map.get(num).setsId((String) value);
                        } else if (column.getColumnName().equals("sPw")) {
                            map.get(num).setsPw((String) value);
                        } else if (column.getColumnName().equals("sName")) {
                            map.get(num).setsName((String) value);
                        } else if (column.getColumnName().equals("sAge")) {
                            map.get(num).setsAge((Integer) value);
                        } else if (column.getColumnName().equals("sGender")) {
                            map.get(num).setsGender((String) value);
                        } else if (column.getColumnName().equals("sMajor")) {
                            map.get(num).setsMajor((String) value);
                        } else {
                            continue;
                        }
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int delete() {
        return delete(s -> true);
    }

    public int delete(Predicate<Student> condition) {
        int count = 0;
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String num = iterator.next();
            Student student = map.get(num);

            if (condition != null) {
                if (condition.test(student)) {
                    iterator.remove();
                    count++;
                }
            }
        }

        return count;
    }

    public Student delete(String sNum) {

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String num = iterator.next();
            Student student = map.get(sNum);
            if (num != null) {
                if (num.equals(sNum)) {
                    iterator.remove();
                    return student;
                }
            }
        }
        return null;
    }

}