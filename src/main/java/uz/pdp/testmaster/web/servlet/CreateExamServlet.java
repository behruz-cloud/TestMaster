package uz.pdp.testmaster.web.servlet;

import uz.pdp.testmaster.web.Repo.CourseRepo;
import uz.pdp.testmaster.web.Repo.ExamRepo;
import uz.pdp.testmaster.web.Repo.GroupRepo;
import uz.pdp.testmaster.web.entity.Course;
import uz.pdp.testmaster.web.entity.Exam;
import uz.pdp.testmaster.web.entity.Groups;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/createExam")
public class CreateExamServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Integer courseId = Integer.parseInt(req.getParameter("courseId"));
            Integer groupId = Integer.parseInt(req.getParameter("groupId"));

            Course course = CourseRepo.getById(courseId);
            Groups groups = GroupRepo.getById(groupId);
            LocalTime startTime = LocalTime.parse(req.getParameter("startTime"));
            LocalTime endTime = LocalTime.parse(req.getParameter("endTime"));
            LocalDate date = LocalDate.parse(req.getParameter("date"));
            Integer modulNumber = Integer.parseInt(req.getParameter("modulNumber"));

            Exam exam = new Exam();
            exam.setCourse(course);
            exam.setGroup(groups);
            exam.setModuleNumber(modulNumber);
            exam.setDate(date);
            exam.setStartTime(startTime);
            exam.setEndTime(endTime);
            ExamRepo.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Imtihon ma'lumotlarini saqlashda xatolik yuz berdi.");
            return;
        }

        resp.sendRedirect("/home.jsp");
    }
}

