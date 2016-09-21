/*******************************************************************************
** Pair.java
** Part of the Java Mutual Information toolbox
** 
** Author: Adam Pocock
** Created: 20/09/2016
**
**  Copyright 2012-2016 Adam Pocock, The University Of Manchester
**  www.cs.manchester.ac.uk
**
**  This file is part of JavaMI.
**
**  JavaMI is free software: you can redistribute it and/or modify
**  it under the terms of the GNU Lesser General Public License as published by
**  the Free Software Foundation, either version 3 of the License, or
**  (at your option) any later version.
**
**  JavaMI is distributed in the hope that it will be useful,
**  but WITHOUT ANY WARRANTY; without even the implied warranty of
**  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**  GNU Lesser General Public License for more details.
**
**  You should have received a copy of the GNU Lesser General Public License
**  along with JavaMI.  If not, see <http://www.gnu.org/licenses/>.
**
*******************************************************************************/
package JavaMI;

/**
 * A simple tuple class. Takes two arguments and stores them.
 * 
 * hashCode and equals inspect the pair fields.
 * @author apocock 
 */
public class Pair<T,U> {
    public final T a;
    public final U b;

    public Pair(T a, U b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash ^ a.hashCode() ^ b.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<?, ?> other = (Pair<?, ?>) obj;
        if (this.a != other.a && (this.a == null || !this.a.equals(other.a))) {
            return false;
        }
        if (this.b != other.b && (this.b == null || !this.b.equals(other.b))) {
            return false;
        }
        return true;
    }

}
