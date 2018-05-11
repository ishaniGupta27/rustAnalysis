// Based on
// https://gist.githubusercontent.com/csherratt/7634597/raw/b05699f444ab552eb79a7f7c0e1c5ac235c5222c/mat4.rs
// from
// https://csherratt.github.io/csherratt/blog/2013/11/24/matrix-multiply-in-rust/

struct Mat1000 {
    dat: [[f32; 1000]; 1000]
}

impl Mat1000 {
    pub fn mult_m(a: Mat1000, b: &Mat1000) -> Mat1000
    {
        let mut out = Mat1000 {};

        for i in 0..1000 {
            for j in 0..1000 {
                for k in 0..1000 {
                    out.dat[i][j] += a.dat[i][k] * b.dat[k][j];
                }
            }
        }

        out
    }

    pub fn fill() -> Mat1000
    {
        let mut outRes = Mat1000 {};
        for i in 0..1000 {
            for j in 0..1000 {
                outRes.dat[i][j]= 3; //random
            }
        }

      outRes
    }
}

fn main()
{
    let mut a = fill();

    let b = fill();

    for _ in 0..100_000 {
        a = Mat4::mult_m(a, &b);
    }

}