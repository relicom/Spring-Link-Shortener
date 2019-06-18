package com.example.shortlink.web.controller;

import com.example.shortlink.datarepository.LinkRepository;
import com.example.shortlink.datarepository.VisitorDao;
import com.example.shortlink.dto.*;
import com.example.shortlink.exception.LinkNotFoundException;
import com.example.shortlink.util.Couple;
import com.example.shortlink.util.RandomCharacter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Controller
@RequestMapping("/")
public class HomeController {

    private final static String newLinkNotProceed = "{\"msg\":\"link registration is not proceed cause of not validation\"}";

    @Autowired
    LinkRepository linkRepository;
    @Autowired
    VisitorDao visitorDao;

    @GetMapping
    public String getHome() {
        return "home";
    }

    @PostMapping(value = "/v/add", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String addNewUrl(@Valid @RequestBody Link link, BindingResult bindingResult) {
        System.out.println("binding Result:" + bindingResult.hasErrors() + "|" + (bindingResult.hasErrors() ? bindingResult.getAllErrors().get(0).toString() : "no error detected!"));
        System.out.println("link in add:" + link);
        if (bindingResult.hasErrors() || link.getCustomLink() == null) {
            return newLinkNotProceed;
        } else {
            Url url = new Url();
            url.setDate(System.currentTimeMillis());
            url.setLink(link.getCustomLink());
            url.setUrl(link.getUrl());
            url = linkRepository.saveAndFlush(url);
            if (url != null) {
                return "{\"code\":1,\"msg\":\"url is registered\",\"shortLink\":\""+url.getLink()+"\"}";
            }
        }
        return newLinkNotProceed;
    }

    @PostMapping(value = "/v/new", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public String checkNewUrl(@Valid @RequestBody Link link, BindingResult bindingResult) throws IOException {
        System.out.println("binding Result:" + bindingResult.hasErrors() + "|" + (bindingResult.hasErrors() ? bindingResult.getAllErrors().get(0).toString() : "no error detected!"));
        System.out.println("link:" + link);
        if (bindingResult.hasErrors()) {
            return newLinkNotProceed;
        } else {

            LinkValidation linkValidation = new LinkValidation();
            linkValidation.setUrl(link.getUrl());
            linkValidation.setShortLink(link.getCustomLink());

            boolean isNeedNewShortLink = false;
            if (link.getCustomLink() != null) {
                Integer urlId = linkRepository.getUrlIdByLink(link.getCustomLink());
                if (urlId == null) {
                    linkValidation.setLinkValidationStatus(LinkValidationStatus.FREE);
                } else {
                    linkValidation.setLinkValidationStatus(LinkValidationStatus.EXIST_AND_CHOOSE_ANOTHER);
                    isNeedNewShortLink = true;
                }
            }

            if (link.getCustomLink() == null || isNeedNewShortLink) {
                boolean isShortLinkFree = false;
                String shortLink;
                do {
                    shortLink = RandomCharacter.getRandomString(5);
                    isShortLinkFree = linkRepository.getUrlIdByLink(shortLink) == null;
                    System.out.println("new Short Link : " + shortLink);
                } while (!isShortLinkFree);
                linkValidation.setShortLink(shortLink);
                linkValidation.setLinkValidationStatus(
                        isNeedNewShortLink ?
                                LinkValidationStatus.EXIST_AND_NEW :
                                LinkValidationStatus.NEW);
            }
            System.out.println("linkValidation.toJson : " + linkValidation.toJson());
            return linkValidation.toJson();
        }
    }

    @GetMapping("/{link}")
    public String redirectLinkToUrl(@PathVariable String link, HttpServletRequest request) throws Throwable {
        System.out.println("redirectLinkToUrl : " + link);
        String urlAddressByLink = linkRepository.getUrlAddressByLink(link);
        if (urlAddressByLink != null && !urlAddressByLink.isEmpty()) {
            Visitor visitor = new Visitor();
            visitor.setDate(System.currentTimeMillis());
            visitor.setIp(request.getRemoteAddr());
            visitorDao.insertVisitor(link, visitor);
            return "redirect:" + urlAddressByLink;
        }
        throw new LinkNotFoundException("link not found!");
    }

    @GetMapping("/{link}+")
    public ModelAndView getLinkStatistics(@PathVariable String link) throws Throwable {

        ModelAndView model = new ModelAndView("statistics");
        Couple<Url, Integer> linkStatistics = visitorDao.getLinkStatistics(link);

        if (linkStatistics != null && linkStatistics.isCoupleNotNull()) {
            model.addObject("url", linkStatistics.getFirst().getUrl());
            model.addObject("date", new Date(linkStatistics.getFirst().getDate()).toString());
            model.addObject("totalVisit", linkStatistics.getSecond());
            return model;
        } else {
            throw new LinkNotFoundException("link not found!");
        }

    }

    /**
     * Deprecated it's handled in application.properties file as property defining
     * @param request
     * @return
     * @throws Exception
     */
//    @GetMapping(value = "/static/**")
//    @ResponseBody
//    public byte[] getStaticResources(HttpServletRequest request) throws Exception {
//        String url = request.getRequestURI().toString();
//        URL u = getClass().getResource(url);
//        File file = new File(u.getFile());
//        String mimeType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file);
//        return FileCopyUtils.copyToByteArray(file);
//    }

}
