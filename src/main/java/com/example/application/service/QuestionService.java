package com.example.application.service;
import java.util.*;

import com.example.application.entity.Question;
import com.example.application.repository.QuestionRepository;
import com.example.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuestionService {

    private QuestionRepository questionRepository;
    private UserRepository userRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    Question question;
    List<Question> questions;
    boolean guessedServ = false;
    String guessedAnswer;
    Map<String, String> answerByUser = new HashMap<>();

    //Authentication a = SecurityContextHolder.getContext().getAuthentication();
    //HashMap data = (HashMap) ((OAuth2Authentication) a).getUserAuthentication().getDetails();

    List<Map<String, String>> fixed = Arrays.asList(new LinkedHashMap[10]);

    public Map<String, String> getAnswerByUser() {
        return answerByUser;
    }

    public void setAnswerByUser(String user, String answer) {
        this.answerByUser.put(user, answer);
    }

    @Transactional
    public void setGuessedAnswer(String guessedAnswer) {
            this.guessedAnswer = guessedAnswer;

            if(guessedAnswer.equals(question.getAnswer())) {
                question.setMaskedAnswer(question.getAnswer());
                System.out.println(fixed.get(0));
                //update points
                //this.userRepository.save()
                System.out.println("in if update user: " + answerByUser.keySet().toArray()[0].toString());
                System.out.println("in guessed if---------------- " + question.isGuessed());
                if(!question.isGuessed()) {
                    this.userRepository.updateUserSetPoints(1, answerByUser.keySet().toArray()[0].toString());
                }

                Map<String,String> guessedByUser = new LinkedHashMap<>();
                guessedByUser.
                        put(this.userRepository.findUserNameByUserId(answerByUser.keySet().toArray()[0].toString()),
                                question.getAnswer() );
                fixed.add(guessedByUser);

                guessedServ = false;
                question.setGuessed(true);
            }
        }
        //get question from repo
        public String getQuestionService() {
            return question.getQuestion();
        }

        //get masked answer
        public String getMaskedAnswerService() {

            // System.out.println(question.getMaskedAnswer());
            //question.setAnswer("***ss");
            //System.out.println("getMaskedAnswerService");
            return question.getMaskedAnswer();
        }


        //get random row from repo, masked it
        @Transactional
        @Scheduled(fixedRate = 5000)
        public void sched() {
            //System.out.println("go go go go go!!!!!");
            questions = this.questionRepository.findAll();
            System.out.println("in sced");
            if(!guessedServ) {

                Random rand = new Random();

                System.out.println("in if");
                question = questions.get(rand.nextInt(questions.size()));
                //System.out.println("ds " + question.getAnswer());

                //System.out.println(question.isFirstMaskedIndexInit() + ", " + question.isGuessed());

                if(question.isFirstMaskedIndexInit() && !question.isGuessed()) {
                    question.setMaskedAnswer(Mask(question.getAnswer()));
                }



               // question.setMaskedAnswer(getMaskedAnswer());

                guessedServ = true;

            } else {
                //System.out.println(question.getMaskedAnswer());
                question.setMaskedAnswer(Mask(question.getMaskedAnswer()));
                System.out.println("after calling Mask method");
                System.out.println(question.getMaskedAnswer());
                if(question.isGuessed()) {
                    guessedServ = false;
                    question.setFirstMaskedIndexInit(true);
                }
            }
            //System.out.println("end end");
            //System.out.println(question);
        }





        public String Mask(String answer) { //"България" "***г****"
            //System.out.println("In the beginning: " + answer);

            String maskedAnswer;
            char[] notMaskedAns = question.getAnswer().toCharArray();
            //System.out.println("getanswer " + question.getAnswer());
            char[] ans = answer.toCharArray();
            List<Integer> restNonDiscovered = new ArrayList<Integer>();

            if(question.isFirstMaskedIndexInit())  {
                question.setMaskedIndex(new int[ans.length]); //юююююююю
                for(int i=0; i< question.getMaskedIndex().length; i++) {
                    question.setMaskedIndexIndex(i,0);
                }

            }
            //System.out.println(isFirstMaskedIndexInit());
            int sum = 0;

            Random ran = new Random();

            int mIndex = 0;
            //int item = 0;

    //

            //for (int i=0; i < maskedIndex.length; i++) {

            //	if(ans[i]=="*".charAt(0)) {
            //		maskedIndex[i] = 0; //0101011
            //	}
            //}

            for (int i = 0; i < ans.length; i++) {

                if( (ans[i] != notMaskedAns[i] || question.isFirstMaskedIndexInit() ) && notMaskedAns[i] != ' ' ) {
                    ans[i]= "*".charAt(0);
                }
            }

            //System.out.println(Arrays.toString(maskedIndex));



            //mIndex = ran.nextInt(ans.length);

            for(int k=0; k < question.getMaskedIndex().length; k++) {

                if(question.getMaskedIndexIndex(k)==0) {
                    //System.out.println(mIndex);
                    //System.out.println(Arrays.toString(question.getMaskedIndex()));
                    //break;

                    restNonDiscovered.add(k);

                }
                //3

            }
            mIndex = restNonDiscovered.get(ran.nextInt(restNonDiscovered.size()));


            //System.out.println(Arrays.toString(notMaskedAns));
            for (int j=0; j < ans.length; j++) {

                if((j==mIndex && question.getMaskedIndexIndex(j)==0) || ans[j] == ' ') {

                    //ans[j]= "*".charAt(0); //***г****
                    ans[j] = notMaskedAns[j];
                    //System.out.println("notMasked " + notMaskedAns[j]);
                    question.setMaskedIndexIndex(j, 1);
                    //System.out.println("in if of masking");
                    //System.out.println(Arrays.toString(maskedIndex));
                }

                //00010000



            }

            maskedAnswer = String.valueOf(ans);

            System.out.println(Arrays.toString(question.getMaskedIndex()));

            for (int i=0; i < question.getMaskedIndex().length; i++) {

                sum += question.getMaskedIndexIndex(i);
            }

            System.out.println("sum is: " + sum + " maskedIndex length is: " + question.getMaskedIndex().length);

            if(sum == question.getMaskedIndex().length) {

                question.setGuessed(true);
            }

            //System.out.println(Arrays.toString(ans));
            //System.out.println(mIndex);


            question.setMaskedAnswer(maskedAnswer);

            question.setFirstMaskedIndexInit(false);
            //System.out.println("in mask method " + maskedAnswer);
            return maskedAnswer;
        }

        /*
        //used in old version
        public String getMaskedAnswer() {
            System.out.println("in getmaskedanswer");
            if(question.isFirstMaskedIndexInit() && !question.isGuessed()) {
                question.setMaskedAnswer(Mask(question.getAnswer()));
            }
            return question.getMaskedAnswer();
        }
        */



}
