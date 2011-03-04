/*
 *  Copyright (C) 2011 Faucet Software.
 *
 *  This file is part of ECSys.
 *
 *  ECSys is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  ECSys is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with ECSys.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.faucet.ecsys;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An entity is a simple object that can have Components attached to it.  The
 * components define an entity's behavior.  Entities can also be tagged to make
 * them identifiable, to categorize them, or to group them.
 */
public class Entity {

    private Set<Component> components;
    /* TODO tags are being stored in the entity AND in the EntityManager
     * This uses more memory and makes adding and removing tags slower.  The
     * only upside is that asking an Entity if it has a given tag is slightly
     * faster.  Also asking an Entity for all tags is possible/considerably more
     * efficient.
     */
    private Set<String> tags;

    /**
     * Default constructor, creates an Entity with no components and no tags.
     */
    public Entity() {
        components = new HashSet<Component>();
        tags = new HashSet<String>();
    }

    /**
     * Adds the given tag to this entity.
     * @param tag Tag to be added.
     */
    public void addTag(String tag) {
        tags.add(tag);
        EntityManager.fetchSingleton().addEntityToTag(this, tag);
    }

    /**
     * Removes the given tag from this entity.
     * @param tag Tag to be removed.
     */
    public void removeTag(String tag) {
        tags.remove(tag);
        EntityManager.fetchSingleton().removeEntityFromTag(this, tag);
    }

    /**
     * Returns whether or not entity has the given tag.
     * @param tag given tag to check for
     * @return True of entity has the given tag, false if not.
     */
    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    /**
     * Returns the set of all tags on this entity
     * @return An unmodifiable set of this entity's tags
     */
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }
}
