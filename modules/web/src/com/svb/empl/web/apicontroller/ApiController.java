package com.svb.empl.web.apicontroller;

import com.haulmont.cuba.core.global.*;
import com.svb.empl.entity.Branch;
import com.svb.empl.entity.Post;
import com.svb.empl.web.apirequest.BranchRequest;
import com.svb.empl.web.apirequest.EmplPost;
import com.svb.empl.web.apiresponse.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/apicontroller", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)



public class ApiController {

    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;

    @Inject
    private TimeSource timeSource;

    @Inject
    private FileLoader fileLoader;


    private Logger log = LoggerFactory.getLogger (ApiController.class);


    private CommitContext commitContext;
    
    
 
    @PostMapping ("/processPosts")

    public @ResponseBody
    ApiResponse processPosts (@RequestBody ArrayList<EmplPost> emplPosts) {
    
        ApiResponse apiResponse = new ApiResponse();
        
        log.info("Start process posts");
        
        try {
            CommitContext commitContext = new CommitContext ();
            for (EmplPost emplPost : emplPosts) {
                log.info("Process Post: "+ emplPost.getEmplPostName());
                
                Post post = this.getExistingPost(emplPost.getEmplPostCode());
                
                if ( post == null) {
                    log.info("Create new post");
    
                    post =  this.createNewPost(emplPost);
                } else {
                    log.info("Update Existing post");
    
                    post = this.updateExistingPost(emplPost, post);
  
                }
    
                
                if (post != null) commitContext.addInstanceToCommit(post);
                
            }
            
            dataManager.commit(commitContext);
	
	        apiResponse.setResponseCode("OK");
         
        } catch (Exception e) {
        	e.printStackTrace();
            apiResponse.setResponseCode ("error");
            apiResponse.setResponseErrorText(e.getMessage ());
        }
	    log.info("End process posts");
        return apiResponse;
    }


    private Post getExistingPost ( String postCode) {
        Post result = null;
        
        String searchQuery = "select e from empl_Post e where e.code = :postCode";
	   
        Optional<Post> optionalPost = dataManager.load(Post.class).
                query(searchQuery).
                parameter("postCode", postCode).view("post-view").
                optional();
        
        if (optionalPost.isPresent()) result = optionalPost.get();
        
        
        return result;
    }
    
  
    private Post createNewPost (EmplPost emplPost) {
        
        Post post = metadata.create(Post.class);
        
        post.setCode(emplPost.getEmplPostCode());
        post.setExtid(emplPost.getEmplPostExtID());
        post.setName(emplPost.getEmplPostName());
        post.setUnid(emplPost.getEmplPostUnid());
        
        return post;
        
    }
    
  
    private Post updateExistingPost (EmplPost emplPost ,
                                     Post existingPost) {
    
        if (!existingPost.getName().equalsIgnoreCase(emplPost.getEmplPostName())) {
            existingPost.setName(emplPost.getEmplPostName());
            return existingPost;
        } else {
            return null;
        }

    }
    
    
    
    @PostMapping("/exportBranches")
    public @ResponseBody ApiResponse exportBranches (
            @RequestBody List<BranchRequest> branchRequestList) {
    
        ApiResponse apiResponse = new ApiResponse();
    
        log.info("Начало выгрузки Филиалов");
        try {
    
            CommitContext commitContext = new CommitContext ();
            
            for (BranchRequest branchRequest : branchRequestList) {
            
                Branch branch = this.getBranchbyCode(branchRequest.getCode());
            
                if (branch == null) {
                    branch = this.createBranch(branchRequest);
                }
            
                
                commitContext.addInstanceToCommit(branch);
            
            }
            
            dataManager.commit(commitContext);
            apiResponse.setResponseCode("OK");
        
            
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setResponseCode ("error");
            apiResponse.setResponseErrorText(e.getMessage ());
        }
        log.info("Окончание выгрузки Филиалов");
        return apiResponse;
        
    }
    
    
    /**
     * Поиск филиала по коду
     *
      * @param branchCode - код филиала для поиска
     * @return Экземпляр класса Branch
     */
    private Branch getBranchbyCode (String branchCode) {
        String request = "select e from empl_Branch e where e.code = :branchCode";
        
        Branch branch = null;
        
        Optional<Branch> optionalBranch = dataManager.load(Branch.class).
                query(request).
                parameter("branchCode", branchCode).
                optional();
        
        if (optionalBranch.isPresent()) {
            branch = optionalBranch.get();
        }
        
        return branch;

    }
    
    
    /**
     * Метод создает новую запись Филиала
     *
     * @param branchRequest - данные по филиалу из  запроса
     * @return возвращает данные по  созданной записи Филиала
     */
    private Branch createBranch (BranchRequest branchRequest) {
    
        Branch branch = metadata.create(Branch.class);
        
        branch.setBik(branchRequest.getBik());
        branch.setCode(branchRequest.getCode());
        branch.setCountry(branchRequest.getCountry());
        branch.setMainoffice(branchRequest.getMainOffice());
        branch.setName(branchRequest.getName());
        branch.setOrgname(branchRequest.getOrgName());
        branch.setNumber(branchRequest.getNumber());
        branch.setRegnumber(branchRequest.getRegNumber());
        
        
        return branch;
        
    }
    
}
