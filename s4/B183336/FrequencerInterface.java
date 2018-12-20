package s4.specification;

/**
 * This interface provides the design for a frequency counter.
 */
public interface FrequencerInterface {
    /**
     * Set the data to search.
     *
     * @param target the data to search
     */
    void setTarget(byte target[]);

    /**
     * Set the data to be searched target from.
     *
     * @param space the data to be searched target from
     */
    void setSpace(byte space[]);

    /**
     * Get the frequency count of TARGET.
     *
     * It returns -1 when TARGET is not set or TARGET's length is zero.
     * It returns 0 when SPACE is not set or SPACE's length is zero.
     * Otherwise, returns the frequency of TAGET in SPACE.
     *
     * @return frequency count
     */
    int frequency();

    /**
     * Get the frequency count of subBytes of TARGET.
     *
     * i.e. target[start], taget[start+1], ... , target[end-1].
     * For the incorrect value of START or END, the behavior is undefined.
     *
     * @param start
     * @param end
     * @return frequency count in subBytes
     */
    int subByteFrequency(int start, int end);
}

