package P;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

/* renamed from: P.d  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0150d extends C {

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: P.d$a */
    /* loaded from: classes.dex */
    public static class a extends AnimatorListenerAdapter {

        /* renamed from: a  reason: collision with root package name */
        private final View f674a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f675b = false;

        a(View view) {
            this.f674a = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            View view = this.f674a;
            u.e(view, 1.0f);
            if (this.f675b) {
                view.setLayerType(0, null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            View view = this.f674a;
            if (androidx.core.view.A.G(view) && view.getLayerType() == 0) {
                this.f675b = true;
                view.setLayerType(2, null);
            }
        }
    }

    public C0150d() {
    }

    public C0150d(int i3) {
        U(i3);
    }

    private ObjectAnimator V(View view, float f3, float f4) {
        if (f3 == f4) {
            return null;
        }
        u.e(view, f3);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, u.f735b, f4);
        ofFloat.addListener(new a(view));
        b(new C0149c(view));
        return ofFloat;
    }

    @Override // P.C
    public final ObjectAnimator S(View view, q qVar) {
        float f3;
        Float f4;
        float f5 = 0.0f;
        if (qVar != null && (f4 = (Float) qVar.f726a.get("android:fade:transitionAlpha")) != null) {
            f3 = f4.floatValue();
        } else {
            f3 = 0.0f;
        }
        if (f3 != 1.0f) {
            f5 = f3;
        }
        return V(view, f5, 1.0f);
    }

    @Override // P.C
    public final ObjectAnimator T(View view, q qVar) {
        float f3;
        Float f4;
        u.c();
        if (qVar != null && (f4 = (Float) qVar.f726a.get("android:fade:transitionAlpha")) != null) {
            f3 = f4.floatValue();
        } else {
            f3 = 1.0f;
        }
        return V(view, f3, 0.0f);
    }

    @Override // P.C, P.i
    public final void i(q qVar) {
        super.i(qVar);
        qVar.f726a.put("android:fade:transitionAlpha", Float.valueOf(u.b(qVar.f727b)));
    }
}