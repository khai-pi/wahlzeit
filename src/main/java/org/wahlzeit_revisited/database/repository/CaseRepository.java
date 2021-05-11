/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 * Copyright (c) 2021 by Aron Metzig
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit_revisited.database.repository;


import jakarta.inject.Inject;
import org.wahlzeit_revisited.model.Case;
import org.wahlzeit_revisited.model.CaseFactory;

/*
 * Repository to query Case entities
 */
public class CaseRepository extends AbstractRepository<Case> {

    /*
     * AbstractRepository contract
     */

    @Inject
    public CaseFactory factory;

    @Override
    protected PersistentFactory<Case> getFactory() {
        return factory;
    }

    @Override
    protected String getTableName() {
        return "cases";
    }
}