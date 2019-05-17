package com.svb.empl.web.post;

import com.haulmont.cuba.gui.screen.*;
import com.svb.empl.entity.Post;

@UiController("empl_Post.edit")
@UiDescriptor("Post.xml")
@EditedEntityContainer("postDc")
@LoadDataBeforeShow
public class PostEdit extends StandardEditor<Post> {
}