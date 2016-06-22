package com.warodom.controller;

import com.warodom.domain.Guestbook;
import com.warodom.domain.GuestbookRepository;
import com.warodom.domain.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by wwaro on 7/6/2559.
 */
@Controller
public class GuestbookController {

    @RequestMapping("/index")
    @ResponseBody
    String hello(){
        return "Hello world";
    }

	@Autowired
	private GuestbookRepository gbDao;

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 2;
    private static final int[] PAGE_SIZES = { 2, 4, 6 };

    @RequestMapping("/")
    public String showGuestbookPage(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                  @RequestParam(value = "page", required = false) Integer page,
                                    Model model) {
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize == null ? INITIAL_PAGE_SIZE : pageSize;
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page == null || page < 1) ? INITIAL_PAGE : page - 1;

        Page<Guestbook> guestbooks =  gbDao.findAll(new PageRequest(evalPage, evalPageSize));
        Pager pager = new Pager(guestbooks.getTotalPages(), guestbooks.getNumber(), BUTTONS_TO_SHOW);

        model.addAttribute ("guestbooks", guestbooks);
        model.addAttribute("selectedPageSize", evalPageSize);
        model.addAttribute("pageSizes", PAGE_SIZES);
        model.addAttribute("pager", pager);
        model.addAttribute("name","Werapun");
        return "index";
    }

//    @RequestMapping("/")
//    public String index(@RequestParam(value="name", required=false, defaultValue="Warodom") String name, Model model) {
//
//    	List<Guestbook> gbJohn = gbDao.findByName("John");
//        model.addAttribute("guestbooks", gbDao.findAll() );
//        model.addAttribute("name",name);
//        return "index";
//    }

    @RequestMapping(value="/new", method= RequestMethod.POST)
    public String newMessage(@ModelAttribute Guestbook guestbook, Model model) {
        gbDao.save(new Guestbook(guestbook.getName() , guestbook.getMessage() ));
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{id}" )
    public String deleteMessage(@PathVariable Long id, final RedirectAttributes redirectAttributes){
        gbDao.delete(id);
        redirectAttributes.addFlashAttribute("delete", "success");
        redirectAttributes.addFlashAttribute("id", id);
        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Guestbook g1 = gbDao.findById(id);
        model.addAttribute("guestbook",g1 );
        System.out.println(g1.getName() + " "  + g1.getMessage());
        return "editMessage";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String update(@ModelAttribute Guestbook guestbook, final RedirectAttributes redirectAttributes){
        if ( gbDao.save(guestbook) != null ){
            redirectAttributes.addFlashAttribute("update", "success");
            redirectAttributes.addFlashAttribute("id", guestbook.getId());
        } else {
            redirectAttributes.addFlashAttribute("update", "unsuccess");
        }
        return "redirect:/";
    }
}
