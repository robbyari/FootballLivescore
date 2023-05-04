package L1;

import L1.f;
import L1.f.b;
import R1.l;

/* loaded from: classes.dex */
public abstract class b<B extends f.b, E extends B> implements f.c<E> {

    /* renamed from: e  reason: collision with root package name */
    private final l<f.b, E> f602e;

    /* renamed from: f  reason: collision with root package name */
    private final f.c<?> f603f;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [R1.l<? super L1.f$b, ? extends E extends B>, R1.l<L1.f$b, E extends B>] */
    public b(f.c<B> cVar, l<? super f.b, ? extends E> lVar) {
        S1.l.f(cVar, "baseKey");
        this.f602e = lVar;
        this.f603f = cVar instanceof b ? (f.c<B>) ((b) cVar).f603f : cVar;
    }

    public final boolean a(f.c<?> cVar) {
        S1.l.f(cVar, "key");
        return cVar == this || this.f603f == cVar;
    }

    /* JADX WARN: Incorrect return type in method signature: (LL1/f$b;)TE; */
    public final f.b b(f.b bVar) {
        S1.l.f(bVar, "element");
        return (f.b) this.f602e.k(bVar);
    }
}