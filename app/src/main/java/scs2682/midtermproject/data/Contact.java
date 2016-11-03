package scs2682.midtermproject.data;

import android.support.annotation.NonNull;
public final class Contact {
    @NonNull
    public final String firstName;

    @NonNull
    public final String lastName;

    @NonNull
    public final Integer gender;

    public Contact(@NonNull final String firstName, @NonNull final String lastName, Integer gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
