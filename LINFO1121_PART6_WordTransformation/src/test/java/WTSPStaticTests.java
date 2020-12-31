//package tests;

import be.ac.ucl.info.javagrading.Grade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

//import student.GlobalWarmingImpl;
//import student.GlobalWarming;

@RunWith(Parameterized.class)
public class WTSPStaticTests
{
    private Instance instance;

    public WTSPStaticTests(Instance instance) {
        this.instance = instance;
    }

    @Test
    @Grade(value = 1)
    public void staticTest() {
        assertEquals(instance.cost, WordTransformationSP.minimalCost(instance.from, instance.to));
    }

    @Parameterized.Parameters
    public static Instance[] data() {
        return new Instance[]{
                new Instance("wdaowkl", "aowlwkd", 12),
                new Instance("fxuldkv", "kfdxvul", 13),
                new Instance("bzwxnxg", "wxbxgnz", 12),
                new Instance("hkhddmc", "ckhddhm", 9),
                new Instance("fcavjtb", "bftjcva", 12),
                new Instance("qqbtghc", "tbqhqcg", 10),
                new Instance("zexqiig", "xezgiqi", 8),
                new Instance("ikstclp", "ktsilpc", 9),
                new Instance("cuwpysz", "cpysuzw", 10),
                new Instance("cvnooos", "oconosv", 10),
                new Instance("zumnlit", "zutnlmi", 8),
                new Instance("wefscav", "aecvsfw", 14),
                new Instance("pvgzoxg", "zvgxpgo", 12),
                new Instance("lgeiyzm", "gezymli", 11),
                new Instance("ofimxuj", "xumifoj", 8),
                new Instance("bvzjphs", "jhbzspv", 12),
                new Instance("nnyiqgx", "xiyqngn", 13),
                new Instance("lqllyat", "qlltayl", 6),
                new Instance("uacfnsi", "aucinfs", 7),
                new Instance("vfwtotc", "ofwttcv", 12),
                new Instance("ftfxxha", "txfxhaf", 11),
                new Instance("defrhmz", "zefdrhm", 12),
                new Instance("mrxcrbk", "cbrkrxm", 13),
                new Instance("dkwohei", "odkhewi", 10),
                new Instance("ahraabk", "akbraah", 9),
                new Instance("lgwhpak", "wkphgal", 13),
                new Instance("wjeekzr", "rwejkez", 10),
                new Instance("xotudui", "ouuxitd", 13),
                new Instance("ucuadky", "cuydauk", 8),
                new Instance("kcraftl", "tlafcrk", 13)
        };
    }

    private static class Instance {
        public String from;
        public String to;
        public int cost;

        public Instance(String from, String to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

    }

}

