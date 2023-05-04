package P;

import android.view.View;
import android.view.WindowId;

/* loaded from: classes.dex */
final class D implements E {

    /* renamed from: a  reason: collision with root package name */
    private final WindowId f658a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public D(View view) {
        this.f658a = view.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof D) && ((D) obj).f658a.equals(this.f658a);
    }

    public final int hashCode() {
        return this.f658a.hashCode();
    }
}