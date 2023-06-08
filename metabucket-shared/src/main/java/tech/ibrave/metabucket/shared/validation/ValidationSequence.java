package tech.ibrave.metabucket.shared.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

/**
 * Author: anct
 * Date: 08/06/2023
 * #YWNA
 */
@GroupSequence({Default.class, FirstOrder.class, SecondOrder.class})
public interface ValidationSequence {
}
