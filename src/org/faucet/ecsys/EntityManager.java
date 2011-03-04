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


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Stores references to every instantiated Entity.  Can be queried for entities
 * matching a given tag.
 */
public class EntityManager {

    static private EntityManager singleton = null;

    // TODO use apache commons multimap instead
    private Map<String, Set<Entity>> tagToEntityMapper;

    // private constructor, singleton
    private EntityManager() {
        tagToEntityMapper = new HashMap<String, Set<Entity>>();
    }

    /**
     * Adds specified entity to the entity set for a given tag.  Should be
     * called by the entity whenever a tag is added so that EntityManager's
     * mapping of tags to entities remains accurate.
     * @param ent entity to add
     * @param tag tag entity should be added to
     */
    protected void addEntityToTag(Entity ent, String tag) {
        /* TODO ent should never be null, though I do not think a problem
         * will happen if it is. */
        getSetForTag(tag).add(ent);
    }

    /**
     * Removesspecified entity from the entity set for a given tag.  Should be
     * called by the entity whenever a tag is removed so that EntityManager's
     * mapping of tags to entities remains accurate.
     * @param ent entity to remove
     * @param tag tag entity should be removed from
     */
    protected void removeEntityFromTag(Entity ent, String tag) {
        /* TODO ent should never be null, though I do not think a problem
         * will happen if it is. */
        getSetForTag(tag).remove(ent);
    }

    /**
     * Retrieves the set of entities tagged with the given tag
     * @param tag retrieves entities with this tag
     * @return An unmodifiable view of the set of requested entities, or null if
     * the tag doesn't exist
     */
    public Set<Entity> getEntitiesWithTag(String tag) {
        Set<Entity> set = tagToEntityMapper.get(tag);
        if (set == null) {
            // TODO should I be throwing an exception?  returning null is so much less verbose
            // throw new NoSuchTagException();
            return null;
        }
        return Collections.unmodifiableSet(set);
    }

    private Set<Entity> getSetForTag(String tag) {
        Set<Entity> entSet = tagToEntityMapper.get(tag);
        if(entSet == null) {
            entSet = new HashSet<Entity>();
            tagToEntityMapper.put(tag, entSet);
        }
        return entSet;
    }

    /**
     * Fetch the singleton instance of EntityManager.  Global state is bad for
     * OOP, convenient for games.
     * @return instance of EntityManager
     */
    public EntityManager fetchSingleton() {
        if(singleton == null) {
            singleton = new EntityManager();
        }
        return singleton;
    }
}
