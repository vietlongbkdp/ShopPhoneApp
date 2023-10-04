package controllers;

import services.BranchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BranchController", value = "/branch")
public class BranchController extends HttpServlet {
    private BranchService branchService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreate(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "edit":
                showEdit(req, resp);
                break;
            default:
                showListBrand(req, resp);
        }
    }

    private void showEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setAttribute("branch", branchService.getBranch(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("/user/admin/editBranch.jsp").forward(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        branchService.deleteBranch(id);
        resp.sendRedirect("/branch");
    }

    private void showCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user/admin/createBranch.jsp").forward(req, resp);
    }

    private void showListBrand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("branchs", branchService.getBranchs());
        req.getRequestDispatcher("/user/admin/branch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                create(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
        }
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nameBranch = req.getParameter("branchName");
        branchService.editBrach(id, nameBranch);
        resp.sendRedirect("/branch");
    }

    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        String nameBranch = req.getParameter("branchName");
        branchService.createBranch(id, nameBranch);
        resp.sendRedirect("/branch?message=Create Success!");
    }

    @Override
    public void init() throws ServletException {
        branchService = new BranchService();
    }
}
