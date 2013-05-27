/*******************************************************************************
** JointProbabilityState.java
** Part of the Java Mutual Information toolbox
** 
** Author: Adam Pocock
** Created: 20/1/2012
**
**  Copyright 2012 Adam Pocock, The University Of Manchester
**  www.cs.manchester.ac.uk
**
**  This file is part of MIToolboxJava.
**
**  MIToolboxJava is free software: you can redistribute it and/or modify
**  it under the terms of the GNU Lesser General Public License as published by
**  the Free Software Foundation, either version 3 of the License, or
**  (at your option) any later version.
**
**  MIToolboxJava is distributed in the hope that it will be useful,
**  but WITHOUT ANY WARRANTY; without even the implied warranty of
**  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**  GNU Lesser General Public License for more details.
**
**  You should have received a copy of the GNU Lesser General Public License
**  along with MIToolboxJava.  If not, see <http://www.gnu.org/licenses/>.
**
*******************************************************************************/

package JavaMI;

import java.util.HashMap;
import java.util.Set;

/**
 * Calculates the probabilities of each state in a joint random variable.
 * Provides the base for all functions of two variables.
 *
 * @author apocock
 */
public class JointProbabilityState
{
  public final HashMap<Integer,Double> jointProbMap;
  public final HashMap<Integer,Double> firstProbMap;
  public final HashMap<Integer,Double> secondProbMap;

  public final int jointMaxVal;
  public final int firstMaxVal;
  public final int secondMaxVal;

  /**
   * Constructor for the JointProbabilityState class. Takes two data vectors and calculates
   * the joint and marginal probabilities, before storing them in HashMaps.
   *
   * @param  firstVector  Input vector. It is discretised to the floor of each value.
   * @param  secondVector  Input vector. It is discretised to the floor of each value.
   */
  public JointProbabilityState(double[] firstVector, double[] secondVector)
  {
    jointProbMap = new HashMap<Integer,Double>();
    firstProbMap = new HashMap<Integer,Double>();
    secondProbMap = new HashMap<Integer,Double>();

    int firstVal, secondVal, jointVal;
    Integer tmpKey, tmpValue;

    int vectorLength = firstVector.length;
    double doubleLength = firstVector.length;

    //round input to integers
    int[] firstNormalisedVector = new int[vectorLength];
    int[] secondNormalisedVector = new int[vectorLength];
    firstMaxVal = ProbabilityState.normaliseArray(firstVector,firstNormalisedVector);
    secondMaxVal = ProbabilityState.normaliseArray(secondVector,secondNormalisedVector);
   
    jointMaxVal = firstMaxVal * secondMaxVal;

    HashMap<Integer,Integer> jointCountMap = new HashMap<Integer,Integer>();
    HashMap<Integer,Integer> firstCountMap = new HashMap<Integer,Integer>();
    HashMap<Integer,Integer> secondCountMap = new HashMap<Integer,Integer>();

    for (int i = 0; i < vectorLength; i++)
    {
        firstVal = firstNormalisedVector[i];
        secondVal = secondNormalisedVector[i];
        jointVal = firstVal + (firstMaxVal * secondVal);

        tmpKey = jointVal;
        tmpValue = jointCountMap.remove(tmpKey);
        if (tmpValue == null)
        {
            jointCountMap.put(tmpKey,1);
        }
        else
        {
            jointCountMap.put(tmpKey,tmpValue + 1);
        }

        tmpKey = firstVal;
        tmpValue = firstCountMap.remove(tmpKey);
        if (tmpValue == null)
        {
            firstCountMap.put(tmpKey,1);
        }
        else
        {
            firstCountMap.put(tmpKey,tmpValue + 1);
        }

        tmpKey = secondVal;
        tmpValue = secondCountMap.remove(tmpKey);
        if (tmpValue == null)
        {
            secondCountMap.put(tmpKey,1);
        }
        else
        {
            secondCountMap.put(tmpKey,tmpValue + 1);
        }
    }

    for (Integer key : jointCountMap.keySet())
    {
        jointProbMap.put(key,jointCountMap.get(key) / doubleLength);
    }

    for (Integer key : firstCountMap.keySet())
    {
        firstProbMap.put(key,firstCountMap.get(key) / doubleLength);
    }

    for (Integer key : secondCountMap.keySet())
    {
        secondProbMap.put(key,secondCountMap.get(key) / doubleLength);
    }
  }//constructor(double[],double[])
}//class JointProbabilityState
