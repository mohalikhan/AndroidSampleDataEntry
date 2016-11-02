package scs2682.midtermproject.data;

import android.support.annotation.NonNull;
public final class Contact {
    @NonNull
    public final String firstName;

    @NonNull
    public final String lastName;

    public Contact(@NonNull final String firstName, @NonNull final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
