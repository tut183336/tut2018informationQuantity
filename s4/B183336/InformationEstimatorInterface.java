package s4.specification;

public interface InformationEstimatorInterface {
    /**
     * Set the data for computing the information quantities.
     *
     * @param target the data
     */
    void setTarget(byte target[]);

    /**
     * Set the data for sample space to computer probability.
     *
     * @param space the data
     */
    void setSpace(byte space[]);

    /**
     * Estimate information quantity.
     *
     * It returns 0.0 when the TARGET is not set or TARGET's length is zero;
     * It returns Double.MAX_VALUE when the true value is infinite, or SPACE is not set.
     * The behavior is undefined if the true value is finite but larger than Double.MAX_VALUE.
     * Note that this happens only when the SPACE is unreasonably large.
     * We will encounter other problem anyway.
     * Otherwise, it returns the estimation value of information quantity.
     *
     * Information quantity I(S) of string S is defined as follows:
     *
     * I(S) = - \sum_{i=0}^{N} log2 P(ci)
     *
     * where, ci is a i-th character in string S,
     * N is the length of String S,
     * and P(c) is the probability of character c in string S.
     *
     * @return estimated information quantity
     */
    double estimation();
}

