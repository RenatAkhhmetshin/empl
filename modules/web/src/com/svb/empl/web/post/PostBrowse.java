package com.svb.empl.web.post;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.Post;

@UiController("empl_Post.browse")
@UiDescriptor("Posts.xml")
@LookupComponent("postsTable")
@LoadDataBeforeShow
public class PostBrowse extends StandardLookup<Post> {
	public void onRefreshBtnClick() {
	}
}