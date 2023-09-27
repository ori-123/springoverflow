package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.question.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionResponseDTO;
import com.codecool.stackoverflowtw.controller.dto.question.QuestionResponseDetailsDTO;
import com.codecool.stackoverflowtw.dao.AnswerDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.QuestionModel;
import com.codecool.stackoverflowtw.dao.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class QuestionService {


    private final QuestionsDAO questionsDAO;

    private final AnswerDAO answerDAO;

    private  final UserDAO userDAO;
    @Autowired
    public QuestionService(QuestionsDAO questionsDAO, AnswerDAO answerDAO, UserDAO userDAO) {
        this.questionsDAO = questionsDAO;
        this.answerDAO = answerDAO;
        this.userDAO = userDAO;
    }

    public Set<QuestionResponseDTO> getAllQuestions() throws SQLException {

        try {
            Set<QuestionModel> questionModels = questionsDAO.readAll();
            Set<QuestionResponseDTO> questionResponseDTOs = new HashSet<>();

            return convertQuestionModelsToQuestionResponseDTOs(
                    questionResponseDTOs, questionModels);

        } catch (CannotGetJdbcConnectionException e) {
            throw new SQLException(e);
        }

    }

    public QuestionResponseDetailsDTO getQuestionById(int questionId) throws SQLException {

        try {
            QuestionModel questionModel = questionsDAO.readById(questionId);

            long id = questionModel.getId();
            String title = questionModel.getTitle();
            String content = questionModel.getContent();
            LocalDateTime createdAt = questionModel.getCreatedAt();
            Set<Long> answersIds = answerDAO.getAnswersIdsForQuestion(questionId);
            String username = userDAO.getUsernameById(questionModel.getUserId());

            return new QuestionResponseDetailsDTO(id, title, content, createdAt, answersIds, username);

        } catch (CannotGetJdbcConnectionException e) {
            throw new SQLException(e);
        }
    }

    public Set<QuestionResponseDTO> getQuestionsByTitle(String searchQuery) throws SQLException {

        try {
            Set<QuestionResponseDTO> foundQuestions = new HashSet<>();
            Set<QuestionModel> foundQuestionModels = questionsDAO.readByTitle(searchQuery);

            return convertQuestionModelsToQuestionResponseDTOs(
                    foundQuestions, foundQuestionModels);

        } catch (CannotGetJdbcConnectionException e) {
            throw new SQLException(e);
        }
    }

    private Set<QuestionResponseDTO> convertQuestionModelsToQuestionResponseDTOs(Set<QuestionResponseDTO> foundQuestions, Set<QuestionModel> foundQuestionModels) throws SQLException {
        for (QuestionModel questionModel : foundQuestionModels) {
            long id = questionModel.getId();
            String title = questionModel.getTitle();
            LocalDateTime createdAt = questionModel.getCreatedAt();
            int answerCount = answerDAO.getNumberOfAnswersForQuestion(id);
            String username = userDAO.getUsernameById(questionModel.getUserId());

            foundQuestions.add(new QuestionResponseDTO(
                    id, title, createdAt, answerCount, username
            ));
        }
        return foundQuestions;
    }

    public boolean deleteQuestionById(int id) throws SQLException {

        try {


        } catch (CannotGetJdbcConnectionException e) {
            throw new SQLException(e);
        }
        return false;
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
