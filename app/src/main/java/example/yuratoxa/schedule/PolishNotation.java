package example.yuratoxa.schedule;

import android.util.Log;

import java.util.LinkedList;

public class PolishNotation {

    final static private String TAG = "Действие";

    static boolean isDelim(char c) { // тру если пробел
        return c == ' ';
    }

    static boolean isX(char c) { // тру если пробел
        return c == 'x';
    }

    static boolean isOperator(char c) { // возвращяем тру если один из символов ниже
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    static boolean isPlusOrMinus(char c) {
        return c == '-' || c == '+';
    }

    static int priority(char op) {
        switch (op) { // при + или - возврат 1, при * / % 2 иначе -1
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    static void processOperator(LinkedList<Float> st, char op) {
        float l;
        Log.d(TAG, "Пійготовка до першого ремов ласт");
        float r = st.removeLast(); // выдёргиваем из упорядоченного листа последний элемент
        Log.d(TAG, "Після першого ремов, до другого");
        if (!st.isEmpty())
            l = st.removeLast(); // также
        else l = 0;
        Log.d(TAG, "після другого ремов");
        switch (op) { // выполняем действие между l и r в зависимости от оператора в кейсе и результат валим в st
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '%':
                st.add(l % r);
                break;
        }
    }

    public static float eval(String s, float x) {
        LinkedList<Float> st = new LinkedList<>(); // сюда наваливают цифры
        LinkedList<Character> op = new LinkedList<>(); // сюда опрераторы и st и op в порядке поступления
        for (int i = 0; i < s.length(); i++) { // парсим строку с выражением и вычисляем
            char c = s.charAt(i);
            if (isDelim(c))
                continue;
            if (isX(c))
                st.add(x);
            else if (c == '(')
                op.add('(');
            else if (c == ')') {
                while (op.getLast() != '(')
                    processOperator(st, op.removeLast());
                op.removeLast();
            } else if (isOperator(c)) {
                if (!isPlusOrMinus(c)) {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                } else if (Character.isDigit(i) & !Character.isDigit(i - 2)) {
                    String operand = "";
                    while (i < s.length() && Character.isDigit(s.charAt(i)))
                        operand = new StringBuilder().append(operand).append(s.charAt(i++)).toString();
                    --i;
                    if (c == '+')
                        st.add(Float.parseFloat(operand));
                    else st.add(Float.parseFloat("-" + operand));

                } else {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                }
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))
                    operand = new StringBuilder().append(operand).append(s.charAt(i++)).toString();
                --i;
                st.add(Float.parseFloat(operand));
            }
        }
        while (!op.isEmpty())
            processOperator(st, op.removeLast());
        return st.get(0);  // возврат результата
    }

}
