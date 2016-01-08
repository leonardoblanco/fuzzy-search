package com.leonardoblanco.search.fuzzy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonardoblanco.search.fuzzy.entity.Customer;
import com.leonardoblanco.search.fuzzy.repository.CustomerRepository;
import com.leonardoblanco.search.fuzzy.tool.FuzzyTool;

/**
 * 
 * @author Leonardo Blanco
 *
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Value("${fuzzy.search.minimum.percent.score}")
	private int searchMinimumPercentScore;
	
	@Value("${fuzzy.search.minimum.score}")
	private double searchMinimumScore;
	
	@Value("${fuzzy.search.maximum.distance}")
	private int searchMaximumDistance;
	
	@Value("${fuzzy.iso.code.language}")
	private String isoCodeLanguage;
	
	@Value("${fuzzy.iso.code.country}")
	private String isoCodeCountry;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@RequestMapping("/list")
	public String list(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){
		
		logger.info(">> Stating analysis for the data: {}, using default fuzzy method.", name.toLowerCase());
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Customer c : customerRepository.findAll()){
			
			int maxScore = FuzzyTool.getMaxFuzzyScore(c.getName());
			int score = StringUtils.getFuzzyDistance(c.getName().toLowerCase(), name.toLowerCase(), new Locale(isoCodeLanguage,isoCodeCountry));
			int result = (score * 100/maxScore);
			
			logger.info("-- [data_found: {}][min_percent_set: {}][max_score_analyzed: {}][score_analyzed: {}][percent_analyzed: {}]", c.getName(), searchMinimumPercentScore, maxScore, score, result);
			
			if(result > searchMinimumPercentScore){
				customers.add(c);
			}
		}
		
		model.addAttribute("customers", customers);
		
		logger.info("<< Analysis for the data: {}, was ended.", name.toLowerCase());
		
		return "list";
	}
	
	@RequestMapping("/listJaroWinkler")
	public String listJaroWinkler(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){
		
		logger.info(">> Stating analysis for the data: {}, using JaroWinkler fuzzy method.", name.toLowerCase());
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Customer c : customerRepository.findAll()){
			
			double score = StringUtils.getJaroWinklerDistance(c.getName().toLowerCase(), name.toLowerCase());
			
			logger.info("-- [data_found: {}][min_score_set: {}][score_analyzed: {}]", c.getName(), searchMinimumScore, score);
			
			if(score >= searchMinimumScore){
				customers.add(c);
			}
		}
		
		model.addAttribute("customers", customers);
		
		logger.info("<< Analysis for the data: {}, was ended.", name.toLowerCase());
		
		return "list";
	}
	
	@RequestMapping("/listLevenshtein")
	public String listLevenshtein(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name){
		
		logger.info(">> Stating analysis for the data: {}, using Levenshtein fuzzy method.", name.toLowerCase());
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Customer c : customerRepository.findAll()){
			
			int distance = StringUtils.getLevenshteinDistance(c.getName().toLowerCase(), name.toLowerCase());
			
			logger.info("-- [data_found: {}][max_distance_set: {}][distance_analyzed: {}]", c.getName(), searchMaximumDistance, distance);
			
			if(distance <= searchMaximumDistance){
				customers.add(c);
			}
		}
		
		model.addAttribute("customers", customers);
		
		logger.info("<< Analysis for the data: {}, was ended.", name.toLowerCase());
		
		return "list";
	}
	
}
