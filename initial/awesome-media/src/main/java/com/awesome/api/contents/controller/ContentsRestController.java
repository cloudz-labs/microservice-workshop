package com.awesome.api.contents.controller;

import java.util.ArrayList;
import java.util.List;

import com.awesome.api.contents.episode.service.EpisodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.awesome.api.contents.episode.controller.EpisodeRestController;
import com.awesome.api.contents.service.ContentsService;
import com.awesome.api.contents.vo.Content;

@RestController
@RequestMapping("/v1")
public class ContentsRestController {
	
	private static Logger logger = LoggerFactory.getLogger(EpisodeRestController.class);
	
	private ContentsService contentsService;

	private EpisodeService episodeService;
	
	@Autowired
	public ContentsRestController(ContentsService contentsService, EpisodeService episodeService) {
		this.contentsService = contentsService;
		this.episodeService = episodeService;
	}
	
	@RequestMapping(path="/contents", method=RequestMethod.GET, name="getContents")
	public List<Content> getContents(@RequestParam(value = "category") String category) {
		logger.debug("getContents() called!!");
		return contentsService.getContents(category);
	}
	
	@RequestMapping(path="/contents", method=RequestMethod.POST, name="addContents")
	public int addContents(@RequestBody Content content) {
		return contentsService.addContent(content);
	}

	@RequestMapping(path="/contents/{id}/similars", method=RequestMethod.GET, name="getSimilars")
	public List<Content> getSimilars(@PathVariable(value = "id") String id, @RequestParam(value = "category") String category) {
		Content content = episodeService.getContentsDetail(id);
		List<Content> similars = contentsService.getContents(category);
		similars.remove(content);
		return similars;
	}

}
