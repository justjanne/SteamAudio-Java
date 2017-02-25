package de.kuschku.steamaudio.lib.util;

/**
 * Status codes returned by Phonon API functions.
 */
public class IPLerror extends EnumWrapper<IPLerror.Enum> {
    public IPLerror() {
        super(Enum::of);
    }

    public IPLerror(IPLerror.Enum value) {
        super(value, IPLerror.Enum::of);
    }

    public IPLerror(int value) {
        super(value, Enum::of);
    }

    public enum Enum implements IntValuedEnum {
        /**
         * The operation completed successfully.
         */
        IPL_STATUS_SUCCESS(0),

        /**
         * An unspecified error occurred.
         */
        IPL_STATUS_FAILURE(1),

        /**
         * The system ran out of memory.
         */
        IPL_STATUS_OUTOFMEMORY(2),

        /**
         * An error occurred while initializing an external dependency.
         */
        IPL_STATUS_INITIALIZATION(3),

        IPL_STATUS_UNKNOWN(-1);

        private final int value;

        Enum(int value) {
            this.value = value;
        }

        public static Enum of(int value) {
            switch (value) {
                case 0:
                    return IPL_STATUS_SUCCESS;
                case 1:
                    return IPL_STATUS_FAILURE;
                case 2:
                    return IPL_STATUS_OUTOFMEMORY;
                case 3:
                    return IPL_STATUS_INITIALIZATION;
                default:
                    return IPL_STATUS_UNKNOWN;
            }
        }

        public int value() {
            return value;
        }

        public IPLerror wrap() {
            return new IPLerror(this);
        }
    }
}
