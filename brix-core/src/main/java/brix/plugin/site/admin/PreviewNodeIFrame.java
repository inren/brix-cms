package brix.plugin.site.admin;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;

import brix.jcr.api.JcrNode;
import brix.web.BrixRequestCycleProcessor;
import brix.web.nodepage.BrixNodeRequestTarget;
import brix.web.nodepage.BrixPageParameters;

public class PreviewNodeIFrame extends WebMarkupContainer<JcrNode> {

	public PreviewNodeIFrame(String id, IModel<JcrNode> model) {
		super(id, model);
		setOutputMarkupId(true);
	}

	@Override
	protected void onComponentTag(ComponentTag tag) {
		super.onComponentTag(tag);
		
		tag.put("href", getUrl());
	}

	private CharSequence getUrl() {
		BrixPageParameters parameters = new BrixPageParameters();
		IModel<JcrNode> nodeModel = getModel();
		String workspace = nodeModel.getObject().getSession().getWorkspace()
				.getName();
		parameters.setQueryParam(BrixRequestCycleProcessor.WORKSPACE_PARAM,
				workspace);
		CharSequence url = getRequestCycle().urlFor(
				new BrixNodeRequestTarget(nodeModel, parameters));
		return url;
	}
}
