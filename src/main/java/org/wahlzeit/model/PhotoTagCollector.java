/*
 * SPDX-FileCopyrightText: 2006-2009 Dirk Riehle <dirk@riehle.org> https://dirkriehle.com
 * SPDX-License-Identifier: AGPL-3.0-or-later
 */

package org.wahlzeit.model;

import java.util.*;

import org.wahlzeit.utils.*;

/**
 * A PhotoTagCollector provides a method to collect all tags for a given photo.
 */
public class PhotoTagCollector {

	/**
	 * 
	 */
	public void collect(Set<String> tags, Photo photo) {
		if (photo == null) throw new NullPointerException("photo is Null");
		if (tags == null) throw new NullPointerException("tags is null");
		String ownerName = photo.getOwnerName();
		if (!StringUtil.isNullOrEmptyString(ownerName)) {
			String ownerNameAsTag = Tags.asTag(ownerName);
			tags.add("un:" + ownerNameAsTag);
			tags.add("tg:" + ownerNameAsTag);
		}

		String[] photoTags = photo.getTags().asArray();
		for (int i = 0; i < photoTags.length; i++) {
			tags.add("tg:" + photoTags[i]);
		}
	}
	
}
