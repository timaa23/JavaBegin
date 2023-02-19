package program;

import models.Answer;
import models.Question;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        RoleEditor.Start();
//        addQuestion();
//        showQuestions();
        questionsAndAnswersTest();
    }

    public static void questionsAndAnswersTest() {
        try {
            Scanner in = new Scanner(System.in);
            Session context = HibernateSessionUtils.getSessionFactory().openSession();
            Transaction tx = context.beginTransaction();
            Query query = context.createQuery("FROM Question");
            List<Question> listQuestion = query.list();
            double mark = 0, questionCount = listQuestion.size();

            for (Question question : listQuestion) {
                Query tempQuery = context.createQuery("FROM Answer WHERE question_id= '" + question.getId() + "'");
                List<Answer> listQuestionItems = tempQuery.list();
                System.out.println("Питання: " + question.getName());
                for (Answer answer : listQuestionItems) {
                    System.out.print(answer.getText() + "\t");
                }

                System.out.println("\n");
                System.out.print("Введіть варіант відповіді: ");
                String answerStr = in.nextLine();

                for (Answer checkAnswer : listQuestionItems) {
                    if (checkAnswer.isTrue() == true) {
                        if (checkAnswer.getText().equals(answerStr)) {
                            System.out.println("True\t" + checkAnswer.getText() + "\t" + answerStr);
                            mark += 1;
                        }
                    }
                }
            }
            System.out.println("Ваша оцінка: " + (mark / questionCount) * 12 + " балів");

        } catch (Exception ex) {
            System.out.println(ex.getCause());
        }
    }

    private static void showQuestions() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Query query = context.createQuery("FROM Question");
            List<Question> list = query.list();
            for (Question q : list)
                System.out.println(q);
        }
    }

    private static void addQuestion() {
        try (Session context = HibernateSessionUtils.getSessionFactory().openSession()) {
            Scanner in = new Scanner(System.in);
            Transaction tx = context.beginTransaction();

            System.out.println("Вкажіть питання: ");
            String questionText = in.nextLine();

            Question q = new Question();
            q.setName(questionText);
            context.save(q);

            String action = "";
            do {
                System.out.println("Вкажіть відповідь: ");
                String text = in.nextLine();
                System.out.println("1 - Правильно, 2 - Не правильно");
                boolean isTrue = Byte.parseByte(in.nextLine()) == 1;

                Answer answer = new Answer();
                answer.setText(text);
                answer.setTrue(isTrue);
                answer.setQuestion(q);
                context.save(answer);

                System.out.println("0. Вихід");
                System.out.println("1. Наступний варіант");
                System.out.println("->_");

                action = in.nextLine();
            } while (!action.equals("0"));

            tx.commit();
        }
    }

}

