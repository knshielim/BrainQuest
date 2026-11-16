package com.example.brainquest.data

import com.example.brainquest.R

/**
The Questions are based on Cambridge IGCSE past papers
 - Mathematics 0580/22, October/November 2023 (Paper 2 Extended)
 - Biology 0610/22, February/March 2023 (Paper 2 Multiple Choice Extended)
 - Chemistry 0620/22, May/June 2023 (Paper 2 Multiple Choice Extended)
 - Physics 0625/22, May/June 2023 (Paper 2 Multiple Choice Extended)
 - Economics 0455/12, May/June 2023 (Paper 1 Multiple Choice)
 - Accounting 0452/12, May/June 2023 (Paper 1 Multiple Choice)
The other subjects have no multiple-choice papers, so their questions are based on the topics in each subject.
 */

object LearningRepository {

    private fun yt(query: String) = "https://www.youtube.com/results?search_query=" + query.replace(" ", "+")

    private const val MATHS = "Adapted from Cambridge IGCSE Mathematics 0580/22, Oct/Nov 2023"
    private const val BIOLOGY = "Adapted from Cambridge IGCSE Biology 0610/22, Feb/Mar 2023"
    private const val CHEMISTRY = "Adapted from Cambridge IGCSE Chemistry 0620/22, May/June 2023"
    private const val PHYSICS = "Adapted from Cambridge IGCSE Physics 0625/22, May/June 2023"
    private const val ECONOMICS = "Adapted from Cambridge IGCSE Economics 0455/12, May/June 2023"
    private const val ACCOUNTING = "Adapted from Cambridge IGCSE Accounting 0452/12, May/June 2023"

    // 1. MATHEMATICS
    private val maths = Subject(
        "math", "Mathematics", "IGCSE 0580 · number, algebra and calculus",
        R.color.math, R.color.math_light, R.drawable.ic_calculate,
        lessons = listOf(
            Lesson(
                id = "math_number", subjectId = "math",
                title = "Number, Bounds & Mensuration",
                summary = "Mixed numbers, prime factors, indices, bounds and similar shapes.",
                content = "Mixed numbers (no calculator): change to improper fractions and use a common denominator.\n" +
                        "Example: 3 1/8 − 1 3/4 = 25/8 − 14/8 = 11/8 = 1 3/8.\n\n" +
                        "Prime factors: 90 = 2 × 3 × 3 × 5 = 2 × 3² × 5.\n\n" +
                        "Index laws: aᵐ × aⁿ = aᵐ⁺ⁿ, (aᵐ)ⁿ = aᵐⁿ, a^(1/n) = ⁿ√a.\n" +
                        "Example: 3³ᵖ × 3²ᵖ = 729 → 3⁵ᵖ = 3⁶ → p = 1.2.\n\n" +
                        "Bounds: 9 cm to the nearest cm lies between 8.5 cm and 9.5 cm. For the upper bound of a ÷ b, use (upper a) ÷ (lower b).\n\n" +
                        "Similar shapes: length factor k → area factor k² → volume factor k³.\n\n" +
                        "Cylinder: total surface area = 2πrh + 2πr².\n\n" +
                        "Speed–time graphs: gradient = acceleration; area under the graph = distance.",
                keyPoints = listOf(
                    "aᵐ × aⁿ = aᵐ⁺ⁿ and a^(1/n) = ⁿ√a",
                    "Upper bound of a ÷ b = upper a ÷ lower b",
                    "Area factor = k², volume factor = k³",
                    "Speed–time: area = distance"
                ),
                imageRes = R.drawable.ill_maths_number,
                videoUrl = yt("IGCSE maths 0580 bounds indices similar shapes"),
                practice = Question("Without a calculator, work out 2 1/3 ÷ 1 3/4.", listOf("1 1/3", "4 1/12", "1 1/4", "3/4"), 0, "7/3 × 4/7 = 4/3 = 1 1/3.")
            ),
            Lesson(
                id = "math_algebra", subjectId = "math",
                title = "Algebra, Graphs & Calculus",
                summary = "Factorising, formulae, sequences, proportion and differentiation.",
                content = "Factorising by grouping: ax − 2a − x + 2 = a(x − 2) − (x − 2) = (x − 2)(a − 1).\n" +
                        "Difference of two squares: a² − 1 = (a − 1)(a + 1).\n\n" +
                        "Changing the subject: y = 2w² − x → w = ±√((y + x) ÷ 2).\n\n" +
                        "Sequences: 11, 8, 5, 2… → nth term = 14 − 3n. 1, 5, 25, 125… → nth term = 5ⁿ⁻¹.\n\n" +
                        "Inverse proportion: x = k ÷ √w. If w = 16 and x = 3, then k = 12.\n\n" +
                        "Line meets curve: set the equations equal and solve. x + 1 = x² + x − 3 → x = ±2.\n\n" +
                        "Differentiation: if y = axⁿ then dy/dx = naxⁿ⁻¹.",
                keyPoints = listOf(
                    "a² − b² = (a − b)(a + b)",
                    "Linear nth term = difference × n + adjustment",
                    "Inverse proportion: x = k ÷ √w",
                    "d/dx (axⁿ) = naxⁿ⁻¹"
                ),
                imageRes = R.drawable.ill_maths_algebra,
                videoUrl = yt("IGCSE maths 0580 extended differentiation sequences proportion"),
                practice = Question("Factorise x² − 7x + 12.", listOf("(x − 3)(x − 4)", "(x + 3)(x + 4)", "(x − 2)(x − 6)", "(x − 12)(x + 1)"), 0, "Two numbers that multiply to 12 and add to −7: −3 and −4.")
            )
        ),
        questions = listOf(
            Question("Without a calculator, work out 3 1/8 − 1 3/4.", listOf("1 3/8", "1 5/8", "2 3/8", "1 1/2"), 0, "25/8 − 14/8 = 11/8 = 1 3/8. ($MATHS, Q8)"),
            Question("Write 90 as a product of its prime factors.", listOf("2 × 3² × 5", "9 × 10", "2 × 3 × 15", "3² × 10"), 0, "90 = 2 × 3 × 3 × 5. ($MATHS, Q9)"),
            Question("Expand and simplify 2(t + w) + 3(w − t).", listOf("5w − t", "5w + t", "5w − 5t", "w − t"), 0, "2t + 2w + 3w − 3t = 5w − t. ($MATHS, Q10)"),
            Question("Two similar shapes have lengths 5 cm and 9 cm. The smaller area is 16 cm². Find the larger area.", listOf("28.8 cm²", "51.84 cm²", "81 cm²", "144 cm²"), 1, "16 × (9/5)² = 51.84 cm². ($MATHS, Q11)"),
            Question("A car travels at 10 m/s for 12 s, then decelerates uniformly to rest in 4 s. Find the total distance.", listOf("120 m", "140 m", "160 m", "200 m"), 1, "10 × 12 + ½ × 4 × 10 = 140 m. ($MATHS, Q12)"),
            Question("3³ᵖ × 3²ᵖ = 729. Find p.", listOf("0.6", "1.2", "3", "6"), 1, "3⁵ᵖ = 3⁶ → p = 1.2. ($MATHS, Q13)"),
            Question("Simplify (32x¹⁰)^(1/5).", listOf("2x²", "2x⁵", "6.4x²", "32x²"), 0, "⁵√32 = 2 and (x¹⁰)^(1/5) = x². ($MATHS, Q13)"),
            Question("Make w the subject of y = 2w² − x.", listOf("w = ±√((y + x) ÷ 2)", "w = ±√(y + x) ÷ 2", "w = (y + x) ÷ 4", "w = ±√((y − x) ÷ 2)"), 0, "Add x, divide by 2, then square root. ($MATHS, Q14)"),
            Question("Find the LCM of 12x⁸ and 8x¹².", listOf("4x⁸", "24x⁸", "24x¹²", "96x²⁰"), 2, "LCM of 12 and 8 is 24; take the higher power x¹². ($MATHS, Q16)"),
            Question("A solid cylinder has radius 5 cm and height 8 cm. Find its total surface area.", listOf("251 cm²", "330 cm²", "408 cm²", "628 cm²"), 2, "2π(5)(8) + 2π(5²) = 130π ≈ 408 cm². ($MATHS, Q18)"),
            Question("Find the nth term of 11, 8, 5, 2, −1, …", listOf("11 − 3n", "14 − 3n", "3n + 14", "8 − 3n"), 1, "Goes down by 3 → −3n; n = 1 gives 14 − 3 = 11. ($MATHS, Q19)"),
            Question("Find the nth term of 1, 5, 25, 125, …", listOf("5ⁿ", "5ⁿ⁻¹", "5n − 4", "n⁵"), 1, "Multiply by 5 each time, starting from 5⁰ = 1. ($MATHS, Q19)"),
            Question("Area = 55.2 cm² (1 d.p.), length = 9 cm (nearest cm). Find the upper bound of the width.", listOf("5.82 cm", "6.13 cm", "6.14 cm", "6.5 cm"), 3, "55.25 ÷ 8.5 = 6.5 cm. ($MATHS, Q20)"),
            Question("Where does y = x + 1 meet y = x² + x − 3?", listOf("(2, 3) and (−2, −1)", "(2, 3) and (−2, 1)", "(4, 5) and (−4, −3)", "(1, 2) and (−3, −2)"), 0, "x² = 4 → x = ±2. ($MATHS, Q21)"),
            Question("x is inversely proportional to √w. When w = 16, x = 3. Find x in terms of w.", listOf("x = 12 ÷ √w", "x = 48 ÷ w", "x = 3 ÷ √w", "x = 0.75√w"), 0, "3 = k ÷ 4 → k = 12. ($MATHS, Q22)"),
            Question("Simplify (ax − 2a − x + 2) ÷ (a² − 1).", listOf("(x − 2) ÷ (a + 1)", "(x − 2) ÷ (a − 1)", "(x + 2) ÷ (a + 1)", "x ÷ (a + 1)"), 0, "(x − 2)(a − 1) ÷ (a − 1)(a + 1). ($MATHS, Q24)"),
            Question("The derivative of 2ax⁷ + 3xᵏ is 42x⁶ + 15xᵏ⁻¹. Find a and k.", listOf("a = 3, k = 5", "a = 21, k = 5", "a = 3, k = 15", "a = 6, k = 4"), 0, "14a = 42 and 3k = 15. ($MATHS, Q25)")
        )
    )

    // 2. BIOLOGY
    private val biology = Subject(
        "biology", "Biology", "IGCSE 0610 · cells, DNA, disease and evolution",
        R.color.biology, R.color.biology_light, R.drawable.ic_biology,
        lessons = listOf(
            Lesson(
                id = "bio_cells", subjectId = "biology",
                title = "Cells, Enzymes & DNA",
                summary = "Osmosis, enzymes, food tests, DNA and protein synthesis.",
                content = "Osmosis is the net movement of WATER molecules through a partially permeable membrane, from a dilute solution to a more concentrated one.\n\n" +
                        "Enzymes are biological catalysts. Each enzyme's active site has a shape complementary to its substrate, which makes it specific. Enzymes are not used up: at the end of a reaction, only the enzymes and the products remain.\n\n" +
                        "Food tests:\n" +
                        "• Starch → iodine solution (blue-black)\n" +
                        "• Reducing sugar → Benedict's solution (heat; brick red)\n" +
                        "• Protein → biuret (purple)\n" +
                        "• Vitamin C → DCPIP (decolourises)\n\n" +
                        "DNA: the bases pair A–T and C–G. The sequence of bases is used to classify organisms and to code for proteins.\n\n" +
                        "Protein synthesis: mRNA copies the gene in the nucleus, then carries it to a ribosome. The sequence of bases in the mRNA decides the order of amino acids.\n\n" +
                        "Genetic modification: the human insulin gene is inserted into a bacterial PLASMID.",
                keyPoints = listOf(
                    "Osmosis = movement of water only",
                    "Enzyme specificity = complementary active site",
                    "Vitamin C test = DCPIP",
                    "A pairs with T; C pairs with G"
                ),
                imageRes = R.drawable.ill_biology_cells,
                videoUrl = yt("IGCSE biology 0610 enzymes DNA protein synthesis"),
                practice = Question("Which test is used to show that a food contains protein?", listOf("Biuret test", "Benedict's test", "Iodine test", "DCPIP test"), 0, "Biuret solution turns purple when protein is present.")
            ),
            Lesson(
                id = "bio_disease", subjectId = "biology",
                title = "Respiration, Disease & Evolution",
                summary = "Anaerobic respiration, antibodies, antibiotics and natural selection.",
                content = "Anaerobic respiration in yeast: glucose → ethanol (alcohol) + carbon dioxide. In bread-making, the CARBON DIOXIDE makes the dough rise.\n\n" +
                        "Immunity: antibodies are proteins made by lymphocytes. Their amino acid sequence gives them a shape that binds to one SPECIFIC antigen.\n\n" +
                        "Antibiotics kill bacteria but not viruses. Overuse leads to resistance. MRSA is a bacterium that is resistant to some antibiotics, so infections are hard to treat.\n\n" +
                        "Natural selection:\n" +
                        "1. There is variation within a population.\n" +
                        "2. Individuals compete for survival.\n" +
                        "3. Those with useful alleles survive and reproduce.\n" +
                        "4. They pass their alleles on to their offspring.\n\n" +
                        "In artificial selection, HUMANS choose which individuals reproduce. In both types, only certain individuals reproduce and alleles are passed on.",
                keyPoints = listOf(
                    "Yeast in bread: CO₂ makes the dough rise",
                    "Antibodies bind to specific antigens",
                    "MRSA = antibiotic-resistant bacteria",
                    "Selection: survivors reproduce and pass on alleles"
                ),
                imageRes = R.drawable.ill_biology_disease,
                videoUrl = yt("IGCSE biology antibodies antibiotic resistance natural selection"),
                practice = Question("Which cells produce antibodies?", listOf("Lymphocytes", "Phagocytes", "Red blood cells", "Platelets"), 0, "Lymphocytes make antibodies; phagocytes engulf pathogens.")
            )
        ),
        questions = listOf(
            Question("What is used as a means of classification?", listOf("The number of strands of mRNA", "The shape of DNA molecules", "The sequence of bases in DNA", "The types of bases in DNA"), 2, "Closely related organisms have similar DNA base sequences. ($BIOLOGY, Q1)"),
            Question("Which molecule crosses a partially permeable membrane during osmosis?", listOf("Carbon dioxide", "Oxygen", "Urea", "Water"), 3, "Osmosis is the movement of water molecules. ($BIOLOGY, Q5)"),
            Question("Which substance is used to test a food for vitamin C?", listOf("Benedict's solution", "DCPIP", "Ethanol", "Iodine solution"), 1, "Vitamin C decolourises DCPIP. ($BIOLOGY, Q7)"),
            Question("In DNA, what is the correct base pairing?", listOf("T with C", "T with T", "T with G", "T with A"), 3, "A pairs with T, and C pairs with G. ($BIOLOGY, Q8)"),
            Question("What remains at the end of an enzyme-controlled reaction?", listOf("Enzymes and products", "Enzymes and substrates", "Enzymes only", "Products only"), 0, "Enzymes are catalysts, so they are not used up. ($BIOLOGY, Q9)"),
            Question("Which feature explains the specificity of an enzyme?", listOf("The complementary shape of the active site and substrate", "Denaturation at certain pH values", "Temperature changing enzyme activity", "How often enzyme and substrate collide"), 0, "Only a substrate with a complementary shape fits the active site. ($BIOLOGY, Q10)"),
            Question("The amino acid sequence of antibodies allows them to do which job?", listOf("Bind to a specific antigen", "Bind to all pathogens", "Carry out phagocytosis", "Give passive immunity to all diseases"), 0, "Each antibody fits one specific antigen. ($BIOLOGY, Q19)"),
            Question("Why is yeast used in bread-making?", listOf("Aerobic respiration produces alcohol.", "Aerobic respiration produces lactic acid.", "Anaerobic respiration produces alcohol.", "Anaerobic respiration produces carbon dioxide."), 3, "The carbon dioxide makes the dough rise. ($BIOLOGY, Q22)"),
            Question("Why is an MRSA infection difficult to treat?", listOf("MRSA is a bacterium.", "MRSA is a virus.", "MRSA is resistant to some antibiotics.", "MRSA is resistant to some antibodies."), 2, "MRSA has evolved resistance to several antibiotics. ($BIOLOGY, Q27)"),
            Question("Which statement about protein synthesis is correct?", listOf("The gene moves from the nucleus to the cytoplasm.", "Ribosomes assemble amino acids into DNA.", "mRNA remains in the nucleus.", "The amino acid sequence is set by the base sequence in mRNA."), 3, "mRNA carries the code to the ribosome, where its bases decide the amino acid order. ($BIOLOGY, Q30)"),
            Question("Which are true of BOTH natural and artificial selection? (1) Only certain individuals reproduce. (2) Alleles are passed on to offspring. (3) Organisms do not compete.", listOf("1 and 2", "1 and 3", "2 only", "2 and 3"), 0, "Competition happens in natural selection, so statement 3 is false. ($BIOLOGY, Q34)"),
            Question("Which part of a bacterium is genetically modified to make human insulin?", listOf("Cell membrane", "Cell wall", "Plasmids", "The single circular DNA strand"), 2, "The insulin gene is inserted into a plasmid. ($BIOLOGY, Q39)")
        )
    )

    // 3. CHEMISTRY
    private val chemistry = Subject(
        "chemistry", "Chemistry", "IGCSE 0620 · atoms, bonding, moles and reactions",
        R.color.chemistry, R.color.chemistry_light, R.drawable.ic_chemistry,
        lessons = listOf(
            Lesson(
                id = "chem_atoms", subjectId = "chemistry",
                title = "Atoms, Bonding & Moles",
                summary = "Atomic structure, the Periodic Table, structures and the mole.",
                content = "Atomic structure: proton number = number of protons. Nucleon number = protons + neutrons. An atom with 5 protons and 6 neutrons has proton number 5 and nucleon number 11. Its electron arrangement is 2,3, so it is in Period 2 and Group III.\n\n" +
                        "Across a period, the proton number increases. Group I (alkali) metals get more reactive and their melting points fall going down the group.\n\n" +
                        "States: when a substance evaporates or boils, the particles move further apart.\n\n" +
                        "Structures:\n" +
                        "• Giant covalent: diamond and silicon(IV) oxide (very high melting points)\n" +
                        "• Metals conduct when solid because delocalised ELECTRONS can move\n\n" +
                        "The mole: moles = mass ÷ Mᵣ. 16 g of CH₄ (Mᵣ = 16) is 1 mol, but 16 g of O₂ (Mᵣ = 32) is only 0.5 mol. One mole of any gas takes up 24 dm³ at r.t.p.",
                keyPoints = listOf(
                    "Nucleon number = protons + neutrons",
                    "Period = number of shells; Group = outer electrons",
                    "SiO₂ is giant covalent, like diamond",
                    "moles = mass ÷ Mᵣ; 1 mol of gas = 24 dm³"
                ),
                imageRes = R.drawable.ill_chemistry_atoms,
                videoUrl = yt("IGCSE chemistry 0620 atomic structure moles giant covalent"),
                practice = Question("How many moles are in 44 g of carbon dioxide, CO₂? (C = 12, O = 16)", listOf("1 mol", "2 mol", "0.5 mol", "44 mol"), 0, "Mᵣ = 12 + 2 × 16 = 44, so 44 ÷ 44 = 1 mol.")
            ),
            Lesson(
                id = "chem_reactions", subjectId = "chemistry",
                title = "Reactions, Acids & Organic Chemistry",
                summary = "Rates, the Contact process, acids, pollution and alkenes.",
                content = "Rates: a catalyst increases the rate by LOWERING the activation energy. Higher temperature and concentration increase the number of successful collisions.\n\n" +
                        "Contact process: 2SO₂ + O₂ ⇌ 2SO₃ at 450 °C and 200 kPa with a vanadium(V) oxide catalyst.\n\n" +
                        "Acids: a strong acid is a proton (H⁺) donor that is COMPLETELY dissociated in water. Amphoteric oxides (e.g. Al₂O₃, ZnO) react with both acids and bases.\n\n" +
                        "Pollution: sulfur dioxide and oxides of nitrogen cause acid rain.\n\n" +
                        "Organic chemistry: alkenes such as propene have a C=C double bond, so they rapidly decolourise bromine water. Alkanes do not.\n\n" +
                        "Tests: carbon dioxide turns limewater milky. Ammonia (from an ammonium salt heated with NaOH) turns damp red litmus blue.",
                keyPoints = listOf(
                    "Catalysts lower the activation energy",
                    "Strong acid = fully dissociated proton donor",
                    "SO₂ + NOₓ → acid rain",
                    "Alkenes decolourise bromine water"
                ),
                imageRes = R.drawable.ill_chemistry_reactions,
                videoUrl = yt("IGCSE chemistry 0620 rates catalysts contact process alkenes"),
                practice = Question("Which gas turns limewater milky?", listOf("Carbon dioxide", "Oxygen", "Hydrogen", "Ammonia"), 0, "CO₂ reacts with limewater to form a white precipitate of calcium carbonate.")
            )
        ),
        questions = listOf(
            Question("Which changes of ethanol make its particles move further apart? (1) condensation (2) evaporation (3) freezing (4) boiling", listOf("1 and 2", "1 and 3", "2 and 4", "3 and 4"), 2, "Evaporation and boiling change a liquid into a gas. ($CHEMISTRY, Q1)"),
            Question("An atom of X has 5 protons, 6 neutrons and 5 electrons. Which are correct? (1) Atomic number 6 (2) Nucleon number 11 (3) In Group II (4) In Period 2", listOf("1 and 3", "1 and 4", "2 and 3", "2 and 4"), 3, "Nucleon number = 5 + 6 = 11. Electrons 2,3 → Period 2, Group III. ($CHEMISTRY, Q2)"),
            Question("Which structure does silicon(IV) oxide most closely resemble?", listOf("Carbon dioxide", "Diamond", "Graphite", "Sodium chloride"), 1, "SiO₂ is a giant covalent structure, like diamond. ($CHEMISTRY, Q4)"),
            Question("Solid P conducts electricity. Which particles move in solid P?", listOf("Anions and cations", "Anions only", "Cations and electrons", "Electrons only"), 3, "Solids conduct (e.g. metals, graphite) through moving electrons. Ions cannot move in a solid. ($CHEMISTRY, Q5)"),
            Question("Which sample contains the largest number of molecules?", listOf("16 g of methane, CH₄", "16 g of oxygen, O₂", "16 g of phosphorus, P₄", "16 dm³ of methane at r.t.p."), 0, "16 g CH₄ = 1 mol; 16 g O₂ = 0.5 mol; 16 dm³ = 0.67 mol; 16 g P₄ = 0.13 mol. ($CHEMISTRY, Q7)"),
            Question("Which change increases the rate of reaction by DECREASING the activation energy?", listOf("Adding a catalyst", "Using smaller pieces of solid", "Increasing concentration", "Increasing temperature"), 0, "Catalysts provide a route with a lower activation energy. ($CHEMISTRY, Q12)"),
            Question("Which conditions are used in the Contact process (SO₂ + O₂ → SO₃)?", listOf("300 °C, 200 kPa, iron", "300 °C, 20 000 kPa, V₂O₅", "450 °C, 200 kPa, V₂O₅", "450 °C, 20 000 kPa, iron"), 2, "450 °C, 200 kPa and a vanadium(V) oxide catalyst. ($CHEMISTRY, Q13)"),
            Question("What is the definition of a strong acid?", listOf("A proton acceptor, completely dissociated", "A proton acceptor, partially dissociated", "A proton donor, completely dissociated", "A proton donor, partially dissociated"), 2, "Acids donate H⁺; strong acids fully dissociate. ($CHEMISTRY, Q16)"),
            Question("Which statement about amphoteric oxides is correct?", listOf("They are made from an acidic and a basic oxide.", "They react with water to give pH 7.", "They react with both acids and bases.", "They react with neither acids nor bases."), 2, "Amphoteric oxides such as Al₂O₃ react with both. ($CHEMISTRY, Q17)"),
            Question("Which statement about alkali metals is correct?", listOf("Lithium is more dense than sodium.", "Sodium is more reactive than potassium.", "Sodium has a higher melting point than potassium.", "They are in Group II."), 2, "Melting points decrease down Group I. ($CHEMISTRY, Q21)"),
            Question("Which compounds both contribute to acid rain?", listOf("CO and CO₂", "CO and oxides of nitrogen", "Oxides of nitrogen and SO₂", "SO₂ and CO₂"), 2, "SO₂ and NOₓ dissolve in rainwater to form acids. ($CHEMISTRY, Q25)"),
            Question("Which compound rapidly decolourises aqueous bromine?", listOf("Propane", "Propanoic acid", "Propanol", "Propene"), 3, "Propene is an alkene with a C=C bond. ($CHEMISTRY, Q31)"),
            Question("Salt X gives a gas that turns limewater milky with acid, and a gas that turns litmus blue with NaOH. What is X?", listOf("CaCO₃", "(NH₄)₂CO₃", "NH₄NO₃", "ZnCO₃"), 1, "Carbonate → CO₂; ammonium → NH₃. ($CHEMISTRY, Q40)")
        )
    )

    // 4. PHYSICS
    private val physics = Subject(
        "physics", "Physics", "IGCSE 0625 · forces, energy, atoms and space",
        R.color.physics, R.color.physics_light, R.drawable.ic_physics,
        lessons = listOf(
            Lesson(
                id = "phy_forces", subjectId = "physics",
                title = "Forces, Motion & Energy",
                summary = "Resultant forces, terminal velocity, mass vs weight and efficiency.",
                content = "Forces at right angles: find the resultant with Pythagoras. 3 N and 4 N → 5 N.\n\n" +
                        "F = ma, so a resultant force changes velocity: Δv = (F × t) ÷ m.\n\n" +
                        "Mass (kg) is the amount of matter and is the same everywhere. Weight is a FORCE: W = mg. g is smaller on Mars than on Earth.\n\n" +
                        "Terminal velocity: a falling object starts with acceleration g. Air resistance increases until it equals the weight; then the acceleration is 0.\n\n" +
                        "Efficiency = useful output ÷ total input. A 30% efficient turbine giving 2.5 MW needs an input of 2.5 ÷ 0.30 ≈ 8.3 MW.\n\n" +
                        "Echoes: distance = speed × time ÷ 2 (the sound travels there and back).",
                keyPoints = listOf(
                    "Resultant of perpendicular forces = √(F₁² + F₂²)",
                    "Δv = Ft ÷ m",
                    "Weight is a force; mass is not",
                    "Input = output ÷ efficiency"
                ),
                imageRes = R.drawable.ill_physics_forces,
                videoUrl = yt("IGCSE physics 0625 resultant force terminal velocity efficiency"),
                practice = Question("A 2 kg trolley accelerates at 3 m/s². What resultant force acts on it?", listOf("6 N", "1.5 N", "5 N", "0.67 N"), 0, "F = ma = 2 × 3 = 6 N.")
            ),
            Lesson(
                id = "phy_space", subjectId = "physics",
                title = "Electricity, Atoms & Space",
                summary = "Static charge, transformers, isotopes, half-life and the Universe.",
                content = "Static electricity: a rod becomes positive when it LOSES electrons. Only electrons move.\n\n" +
                        "Transformers (100% efficient): Vp × Ip = Vs × Is. Stepping 240 V down to 60 V makes the current 4 times bigger.\n\n" +
                        "Isotopes have the same number of protons but different numbers of neutrons.\n\n" +
                        "Half-life: subtract the background count first, halve it, then add the background back. Example: (200 − 20) ÷ 2 + 20 = 110 counts/min in total, so 90 counts/min is due to the source.\n\n" +
                        "Space: stars are powered by nuclear FUSION of hydrogen into helium. Light from distant galaxies is redshifted, which shows the Universe is expanding. Hubble constant H₀ = recession speed ÷ distance.",
                keyPoints = listOf(
                    "Positive charge = lost electrons",
                    "Vp × Ip = Vs × Is",
                    "Isotopes: same protons, different neutrons",
                    "H₀ = recession speed ÷ distance"
                ),
                imageRes = R.drawable.ill_physics_space,
                videoUrl = yt("IGCSE physics 0625 transformers half-life Hubble constant"),
                practice = Question("A galaxy moves away at 7000 km/s and is 100 Mpc away. What value of the Hubble constant does this give?", listOf("70 km/s per Mpc", "700 km/s per Mpc", "0.014 km/s per Mpc", "7 × 10⁵ km/s per Mpc"), 0, "7000 ÷ 100 = 70 km/s per Mpc.")
            )
        ),
        questions = listOf(
            Question("Forces of 3 N and 4 N act at right angles. What is the size of the resultant?", listOf("1 N", "5 N", "7 N", "12 N"), 1, "√(3² + 4²) = 5 N. ($PHYSICS, Q1)"),
            Question("A ball falls from a cliff and reaches terminal velocity. What are its initial and final accelerations?", listOf("0 and 0", "0 and g", "g and 0", "g and g"), 2, "At first only weight acts; at terminal velocity the forces balance. ($PHYSICS, Q2)"),
            Question("Sphere P weighs 10 N on Earth. Sphere Q weighs 10 N on Mars. g is greater on Earth. Which is correct?", listOf("P and Q have the same mass.", "The mass of P is less than the mass of Q.", "On Mars, P and Q weigh the same.", "On Earth, Q weighs less than 10 N."), 1, "m = W ÷ g, and g is bigger on Earth, so P has less mass. ($PHYSICS, Q5)"),
            Question("A 2.0 N resultant force acts on a 3.0 kg object for 6.0 s. What is the change in velocity?", listOf("0.25 m/s", "1.0 m/s", "4.0 m/s", "36 m/s"), 2, "Δv = Ft ÷ m = 2 × 6 ÷ 3 = 4.0 m/s. ($PHYSICS, Q9)"),
            Question("A wind turbine is 30% efficient and outputs 2.5 MW. What is its power input?", listOf("0.75 MW", "8.3 MW", "75 MW", "83 MW"), 1, "2.5 ÷ 0.30 ≈ 8.3 MW. ($PHYSICS, Q11)"),
            Question("A boy hears an echo from a building 2.2 s after shouting. Sound travels at 330 m/s. How far away is the building?", listOf("150 m", "300 m", "360 m", "730 m"), 2, "330 × 2.2 ÷ 2 = 363 m ≈ 360 m. ($PHYSICS, Q22)"),
            Question("A plastic rod rubbed with a cloth becomes positively charged. Why?", listOf("It gained electrons.", "It gained neutrons.", "It lost electrons.", "It lost neutrons."), 2, "Only electrons move; losing negative charge leaves the rod positive. ($PHYSICS, Q24)"),
            Question("A 100% efficient transformer steps 240 V down to 60 V. The primary current is 0.50 A. What is the secondary current?", listOf("0.13 A", "0.50 A", "2.0 A", "8.0 A"), 2, "240 × 0.50 = 60 × Is → Is = 2.0 A. ($PHYSICS, Q30)"),
            Question("Nucleus X: 3 protons, 3 neutrons. Y: 3 protons, 4 neutrons. Z: 4 protons, 3 neutrons. Which are isotopes of the same element?", listOf("X, Y and Z", "X and Y only", "X and Z only", "Y and Z only"), 1, "Isotopes have the same number of protons. ($PHYSICS, Q34)"),
            Question("An isotope has a half-life of 8 days. The count rate is 200 counts/min, with a background of 20 counts/min. What is the count rate due to the source after 8 days?", listOf("80 counts/min", "90 counts/min", "100 counts/min", "110 counts/min"), 1, "Source = 200 − 20 = 180; halved = 90. ($PHYSICS, Q36)"),
            Question("Which nuclear reaction powers a star?", listOf("Fission of helium into hydrogen", "Fission of hydrogen into helium", "Fusion of helium into hydrogen", "Fusion of hydrogen into helium"), 3, "Stars fuse hydrogen into helium. ($PHYSICS, Q39)"),
            Question("What is the definition of the Hubble constant?", listOf("A galaxy's recession speed ÷ its distance from Earth", "The change in wavelength due to redshift", "The rate at which stars form", "The age of the Universe"), 0, "H₀ = recession speed ÷ distance. ($PHYSICS, Q40)")
        )
    )

    // 5. COMPUTER SCIENCE
    private val computerScience = Subject(
        "cs", "Computer Science", "IGCSE 0478 · data, logic and algorithms",
        R.color.cs, R.color.cs_light, R.drawable.ic_cs,
        lessons = listOf(
            Lesson(
                id = "cs_data", subjectId = "cs",
                title = "Data Representation",
                summary = "Binary, hexadecimal, shifts, overflow, file size and compression.",
                content = "Binary place values (8-bit): 128, 64, 32, 16, 8, 4, 2, 1.\n" +
                        "Example: 10110110 = 128 + 32 + 16 + 4 + 2 = 182.\n\n" +
                        "Hexadecimal uses 0–9 and A–F (A = 10 … F = 15). 200 = 12 × 16 + 8 = C8. 3F = 3 × 16 + 15 = 63.\n\n" +
                        "A logical left shift of n places multiplies by 2ⁿ. 00010110 (22) shifted 2 places left = 01011000 (88).\n\n" +
                        "Overflow happens when a result is too big for the register. In 8 bits, the maximum is 255, so 200 + 64 = 264 overflows.\n\n" +
                        "Image file size = width × height × colour depth (bits). 1000 × 800 pixels at 8 bits = 6 400 000 bits = 800 000 bytes.\n\n" +
                        "Compression: lossless (e.g. run-length encoding, RLE) keeps all the data. Lossy (e.g. JPEG, MP3) permanently removes some data.\n\n" +
                        "Parity: with even parity, the total number of 1s (including the parity bit) must be even.",
                keyPoints = listOf(
                    "8-bit place values: 128 64 32 16 8 4 2 1",
                    "Left shift by n = × 2ⁿ",
                    "8-bit maximum = 255 → overflow above it",
                    "File size = width × height × colour depth"
                ),
                imageRes = R.drawable.ill_cs_data,
                videoUrl = yt("IGCSE computer science 0478 binary hexadecimal overflow file size"),
                practice = Question("Convert the binary number 01100100 to denary.", listOf("100", "96", "104", "36"), 0, "64 + 32 + 4 = 100.")
            ),
            Lesson(
                id = "cs_algorithms", subjectId = "cs",
                title = "Logic, Algorithms & Programming",
                summary = "Logic gates, pseudocode, searching, validation and translators.",
                content = "Logic gates:\n" +
                        "• AND outputs 1 only if both inputs are 1.\n" +
                        "• OR outputs 1 if either input is 1.\n" +
                        "• NOT reverses the input.\n" +
                        "• NAND and NOR are AND and OR followed by NOT.\n\n" +
                        "Pseudocode loop example:\n" +
                        "Total ← 0\n" +
                        "FOR i ← 1 TO 5\n" +
                        "    Total ← Total + i\n" +
                        "NEXT i\n" +
                        "OUTPUT Total   → outputs 15\n\n" +
                        "Searching: a linear search checks every item in turn. A binary search needs SORTED data and halves the list each time.\n\n" +
                        "Validation checks that data is reasonable (range, length, type, presence, format). Verification checks that data was copied correctly (double entry, visual check).\n\n" +
                        "Translators: a compiler translates the whole program at once into an executable file. An interpreter translates and runs one line at a time.",
                keyPoints = listOf(
                    "AND = both 1; OR = either 1; NOT = reverse",
                    "Binary search needs sorted data",
                    "Range check = value between limits",
                    "Interpreter = line by line; compiler = whole program"
                ),
                imageRes = R.drawable.ill_cs_algorithms,
                videoUrl = yt("IGCSE computer science 0478 logic gates pseudocode validation"),
                practice = Question("A = 1 and B = 1. What is the output of A NAND B?", listOf("0", "1", "2", "It depends on C"), 0, "A AND B = 1, and NOT 1 = 0.")
            )
        ),
        questions = listOf(
            Question("Convert the binary number 10110110 to denary.", listOf("182", "186", "174", "150"), 0, "128 + 32 + 16 + 4 + 2 = 182."),
            Question("Convert the denary number 200 to hexadecimal.", listOf("C8", "8C", "B8", "D0"), 0, "200 = 12 × 16 + 8 → C8."),
            Question("Convert the hexadecimal number 3F to denary.", listOf("63", "48", "315", "51"), 0, "3 × 16 + 15 = 63."),
            Question("What is 00010110 after a logical left shift of 2 places?", listOf("01011000", "00000101", "00101100", "10110000"), 0, "Shift every bit 2 places left: 22 × 4 = 88 = 01011000."),
            Question("11001000 and 01000000 are added in an 8-bit register. What happens?", listOf("An overflow error occurs", "The answer is 264", "A parity error occurs", "The result is stored as hexadecimal"), 0, "200 + 64 = 264, which is more than 255, the 8-bit maximum."),
            Question("An image is 1000 × 800 pixels with a colour depth of 8 bits. What is its file size?", listOf("100 000 bytes", "800 000 bytes", "6 400 000 bytes", "8 000 bytes"), 1, "1000 × 800 × 8 = 6 400 000 bits ÷ 8 = 800 000 bytes."),
            Question("Which is a LOSSLESS compression method?", listOf("Run-length encoding (RLE)", "Reducing the colour depth", "Removing sounds people cannot hear", "Reducing the resolution"), 0, "RLE stores repeated values as one value + count, so no data is lost."),
            Question("Using EVEN parity, which parity bit is added to the 7-bit data 1011001?", listOf("0", "1", "2", "It needs odd parity"), 0, "There are already four 1s (even), so the parity bit is 0."),
            Question("What is the output of NOT (A OR B) when A = 1 and B = 0?", listOf("0", "1", "2", "Undefined"), 0, "A OR B = 1, and NOT 1 = 0. (This is a NOR gate.)"),
            Question("Which validation check makes sure an exam mark is between 0 and 100?", listOf("Range check", "Length check", "Presence check", "Double entry"), 0, "A range check tests that a value lies between an upper and lower limit."),
            Question("Which search algorithm only works if the data is sorted?", listOf("Binary search", "Linear search", "Bubble sort", "Totalling"), 0, "A binary search compares with the middle item and discards half each time."),
            Question("Total ← 0; FOR i ← 1 TO 5: Total ← Total + i; NEXT i; OUTPUT Total. What is output?", listOf("15", "5", "10", "6"), 0, "1 + 2 + 3 + 4 + 5 = 15."),
            Question("Which translator converts and runs a program one line at a time?", listOf("Interpreter", "Compiler", "Assembler", "Linker"), 0, "An interpreter stops when it finds an error, which helps debugging.")
        )
    )

    // 6. ICT
    private val ict = Subject(
        "ict", "ICT", "IGCSE 0417 · hardware, networks, safety and spreadsheets",
        R.color.ict, R.color.ict_light, R.drawable.ic_ict,
        lessons = listOf(
            Lesson(
                id = "ict_hardware", subjectId = "ict",
                title = "Hardware, Storage & Networks",
                summary = "Memory, storage media, sensors and network devices.",
                content = "Memory: RAM is volatile (it loses its contents when the power is off) and holds the programs in use. ROM is non-volatile and stores the start-up instructions (BIOS).\n\n" +
                        "Storage:\n" +
                        "• Magnetic: hard disk drive (HDD)\n" +
                        "• Optical (read by laser): CD, DVD, Blu-ray\n" +
                        "• Solid state: SSD, USB flash drive, memory card\n\n" +
                        "Sensors (temperature, light, pressure) produce analogue signals. An analogue-to-digital converter (ADC) turns them into digital data for the computer.\n\n" +
                        "Networks:\n" +
                        "• A router connects a LAN to the internet and sends data packets between networks.\n" +
                        "• A switch connects devices within a LAN.\n" +
                        "• A WAN covers a large area; the internet is the largest WAN.",
                keyPoints = listOf(
                    "RAM is volatile; ROM is non-volatile",
                    "Optical storage is read by a laser",
                    "Sensors need an ADC",
                    "Router = connects networks / the internet"
                ),
                imageRes = R.drawable.ill_ict_hardware,
                videoUrl = yt("IGCSE ICT 0417 storage devices RAM ROM router"),
                practice = Question("Which of these is an example of solid-state storage?", listOf("USB flash drive", "DVD", "Hard disk drive", "Blu-ray disc"), 0, "Flash drives have no moving parts and store data in electronic circuits.")
            ),
            Lesson(
                id = "ict_safety", subjectId = "ict",
                title = "e-Safety, Security & Spreadsheets",
                summary = "Phishing, pharming, passwords, health and spreadsheet formulas.",
                content = "Security threats:\n" +
                        "• Phishing: a fake email or message that tricks you into giving personal details.\n" +
                        "• Pharming: malicious code redirects you from a real website to a fake one.\n" +
                        "• Strong passwords mix upper and lower case, numbers and symbols, and are not real words.\n" +
                        "• Encryption scrambles data so it cannot be understood without the key.\n" +
                        "• Biometrics (fingerprint, face) identify a person by unique body features.\n\n" +
                        "Health: repetitive strain injury (RSI) comes from long periods of typing. Use wrist rests and take breaks.\n\n" +
                        "Spreadsheets:\n" +
                        "• =SUM(B2:B10) adds cells B2 to B10.\n" +
                        "• \$B\$1 is an absolute reference: it doesn't change when copied.\n" +
                        "• =IF(A1>=50,\"Pass\",\"Fail\") gives \"Pass\" when A1 is 50 or more.\n\n" +
                        "Verification (e.g. double entry) checks data was copied correctly.",
                keyPoints = listOf(
                    "Phishing = fake email; pharming = fake website redirect",
                    "Encryption makes intercepted data unreadable",
                    "\$B\$1 is an absolute cell reference",
                    "RSI is caused by repetitive typing"
                ),
                imageRes = R.drawable.ill_ict_safety,
                videoUrl = yt("IGCSE ICT 0417 phishing pharming spreadsheet absolute reference"),
                practice = Question("A cell contains =IF(C3>100,\"High\",\"Low\") and C3 = 100. What is displayed?", listOf("Low", "High", "100", "An error"), 0, "100 is not greater than 100, so the result is \"Low\".")
            )
        ),
        questions = listOf(
            Question("Which type of memory is volatile?", listOf("RAM", "ROM", "SSD", "DVD"), 0, "RAM loses its contents when the power is switched off."),
            Question("Which storage medium is optical?", listOf("Blu-ray disc", "Hard disk drive", "USB flash drive", "SD card"), 0, "Optical media are read and written using a laser."),
            Question("Which device connects a home LAN to the internet and directs data packets between networks?", listOf("Router", "Switch", "Monitor", "Scanner"), 0, "Routers forward packets between different networks."),
            Question("A greenhouse uses a temperature sensor and a computer. Which component converts the sensor's signal for the computer?", listOf("Analogue-to-digital converter", "Digital-to-analogue converter", "Router", "Actuator"), 0, "Sensors give analogue signals; computers need digital data."),
            Question("What is phishing?", listOf("A fake email that tricks users into giving personal details", "Code that secretly redirects you to a fake website", "Software that records keystrokes", "Sending large numbers of adverts"), 0, "Phishing uses fake messages that look genuine."),
            Question("What is pharming?", listOf("Malicious code that redirects users to a fake website", "A fake email asking for bank details", "A virus that deletes files", "Copying software illegally"), 0, "Pharming changes where a website address takes you."),
            Question("Which is the strongest password?", listOf("T7#qLm9!xR", "password123", "Aina2009", "qwerty"), 0, "It mixes upper and lower case, numbers and symbols, and is not a real word."),
            Question("Which formula adds the values in cells B2 to B10?", listOf("=SUM(B2:B10)", "=ADD(B2-B10)", "=B2+B10", "=TOTAL(B2,B10)"), 0, "SUM with a range B2:B10 adds every cell in it."),
            Question("Which is an absolute cell reference?", listOf("\$B\$1", "B1", "B\$1", "\$B1"), 0, "Both the column and the row are fixed with \$ signs."),
            Question("A1 = 50. What does =IF(A1>=50,\"Pass\",\"Fail\") display?", listOf("Pass", "Fail", "50", "TRUE"), 0, "50 is greater than or equal to 50, so the condition is true."),
            Question("What is the main purpose of encryption?", listOf("To make data unreadable if it is intercepted", "To make files smaller", "To stop viruses", "To back up data"), 0, "Only someone with the key can decrypt the data."),
            Question("Which health problem is linked to long periods of typing?", listOf("Repetitive strain injury (RSI)", "Hearing loss", "Food poisoning", "Sunburn"), 0, "RSI affects the wrists and hands; use wrist rests and take breaks."),
            Question("A user types data twice and the computer compares the two entries. What is this?", listOf("Verification", "Validation", "Encryption", "Compression"), 0, "Double entry is a verification method.")
        )
    )

    // 7. ENGLISH
    private val english = Subject(
        "english", "English", "IGCSE 0510 ESL · grammar, register and vocabulary",
        R.color.english, R.color.english_light, R.drawable.ic_book,
        lessons = listOf(
            Lesson(
                id = "eng_grammar", subjectId = "english",
                title = "Advanced Grammar Structures",
                summary = "Conditionals, wishes, inversion, passives and reported questions.",
                content = "Conditionals:\n" +
                        "• Third: If I had revised, I would have passed.\n" +
                        "• Mixed (past cause → present result): If she had checked the map, she wouldn't be lost now.\n\n" +
                        "Wishes: I wish I had had more time (past regret). It's high time we started (+ past simple).\n\n" +
                        "Inversion (formal): Not only did he arrive late… / Hardly had we reached the station when… / Never have I seen…\n\n" +
                        "Modal passive: modal + be + past participle. Mobile phones must not be used.\n\n" +
                        "Reported questions use statement word order: \"Where do you live?\" → He asked me where I lived.\n\n" +
                        "Relative pronouns: who (people), which (things), whose (possession).",
                keyPoints = listOf(
                    "Mixed conditional: If + past perfect, would + verb",
                    "Negative adverb first → invert",
                    "must/can/should + be + past participle",
                    "Reported questions: no question word order"
                ),
                imageRes = R.drawable.ill_english_grammar,
                videoUrl = yt("mixed conditionals inversion reported questions advanced grammar"),
                practice = Question("\"Never ___ such a beautiful sunset.\"", listOf("I have seen", "have I seen", "I saw", "did I saw"), 1, "After \"Never\" at the start, invert: have I seen.")
            ),
            Lesson(
                id = "eng_writing", subjectId = "english",
                title = "Writing Skills: Register & Vocabulary",
                summary = "Formal style, linking words, word formation and attitude.",
                content = "IGCSE ESL writing tasks (emails, articles, reports) reward the right register and well-linked ideas.\n\n" +
                        "Register: formal emails open with \"Dear Mr Tan,\" or \"Dear Sir or Madam,\". Close with \"Yours sincerely\" (name known) or \"Yours faithfully\" (name unknown). Prefer formal verbs: find out → ascertain; get → obtain.\n\n" +
                        "Linking words:\n" +
                        "• Contrast: however, nevertheless, whereas\n" +
                        "• Result: therefore, consequently\n" +
                        "• Addition: furthermore, moreover\n\n" +
                        "Word formation: discover → discovery (noun) → discoverer (person).\n\n" +
                        "Collocations: have an impact on, make a decision, do research.\n\n" +
                        "Agreement: \"The number of students HAS increased.\"\n\n" +
                        "Attitude: words like surely, outdated and no sensible person show a critical tone.",
                keyPoints = listOf(
                    "Yours sincerely (name known) / faithfully (unknown)",
                    "nevertheless = contrast",
                    "After a possessive, use a noun",
                    "The number of… has"
                ),
                imageRes = R.drawable.ill_english_writing,
                videoUrl = yt("IGCSE ESL formal email register linking words"),
                practice = Question("Which ending suits a formal email that began \"Dear Sir or Madam\"?", listOf("Yours faithfully,", "Yours sincerely,", "Cheers,", "Love,"), 0, "If you don't know the name, end with \"Yours faithfully\".")
            )
        ),
        questions = listOf(
            Question("\"If she ___ the map yesterday, she wouldn't be lost now.\"", listOf("checked", "had checked", "has checked", "would check"), 1, "Mixed conditional: past cause → present result."),
            Question("\"Not only ___ late, but he also forgot his homework.\"", listOf("he arrived", "did he arrive", "he did arrive", "arrived he"), 1, "After \"Not only\" at the start, use inversion."),
            Question("\"I wish I ___ more time to revise last week.\"", listOf("have", "had", "had had", "would have"), 2, "Past regret: wish + past perfect."),
            Question("\"It's high time we ___ for the exam.\"", listOf("start", "started", "will start", "have started"), 1, "\"It's high time\" + past simple."),
            Question("\"Mobile phones ___ during the examination.\"", listOf("must not use", "must not be used", "must not being used", "must not been used"), 1, "Modal passive: must not + be + past participle."),
            Question("He asked, \"Where do you live?\" Which is the correct reported question?", listOf("He asked me where did I live.", "He asked me where I lived.", "He asked me where do I live?", "He asked me where I live?"), 1, "Statement word order, tense moves back, no question mark."),
            Question("\"The student, ___ essay won the prize, is in Year 10.\"", listOf("who", "which", "whose", "whom"), 2, "\"Whose\" shows possession."),
            Question("\"The number of students taking IGCSE ___ increased this year.\"", listOf("have", "has", "are", "were"), 1, "\"The number of\" is singular."),
            Question("\"The town is popular with tourists; ___, few people live there all year.\"", listOf("therefore", "nevertheless", "furthermore", "similarly"), 1, "\"Nevertheless\" introduces a contrast."),
            Question("Which opening suits a formal email to your principal, Mr Tan?", listOf("Hi Mr Tan!", "Hey there,", "Dear Mr Tan,", "To my principal"), 2, "Formal: Dear + title + surname."),
            Question("Which is the most formal replacement for \"find out\" in a report?", listOf("ascertain", "dig up", "figure out", "suss out"), 0, "The others are informal."),
            Question("\"The new policy had a significant ___ on students' wellbeing.\"", listOf("impact", "affect", "result", "cause"), 0, "The collocation is \"have an impact on\"."),
            Question("\"The scientist's ___ (DISCOVER) changed medicine forever.\"", listOf("discover", "discovery", "discovered", "discovering"), 1, "After a possessive, use a noun."),
            Question("\"Despite new laws, the problem persisted for years.\" What does \"persisted\" mean?", listOf("Continued", "Disappeared", "Improved", "Was solved"), 0, "To persist is to continue to exist."),
            Question("\"Surely no sensible person would still believe this outdated idea.\" The writer's attitude is:", listOf("Critical", "Neutral", "Enthusiastic", "Uncertain"), 0, "\"Surely\", \"no sensible person\" and \"outdated\" show disapproval."),
            Question("\"Hardly ___ the station when the train left.\"", listOf("we had reached", "had we reached", "we reached", "did we reached"), 1, "After \"Hardly\" at the start, invert.")
        )
    )

    // 8. BUSINESS
    private val business = Subject(
        "business", "Business", "IGCSE 0450 · organisation, marketing and finance",
        R.color.business, R.color.business_light, R.drawable.ic_business,
        lessons = listOf(
            Lesson(
                id = "bus_marketing", subjectId = "business",
                title = "Business Organisation & Marketing",
                summary = "Sectors, ownership, stakeholders, motivation and the 4Ps.",
                content = "Economic sectors: primary (extracting raw materials, e.g. farming, fishing, mining), secondary (manufacturing), tertiary (services).\n\n" +
                        "Ownership:\n" +
                        "• Sole trader: one owner with UNLIMITED liability.\n" +
                        "• Private limited company (Ltd): shareholders have LIMITED liability; shares cannot be sold to the public.\n\n" +
                        "Stakeholders: internal (employees, managers, owners) and external (customers, suppliers, government).\n\n" +
                        "Motivation: Maslow's hierarchy starts with physiological needs (food, shelter), then safety, social, esteem and self-actualisation.\n\n" +
                        "Marketing mix (4Ps): Product, Price, Place, Promotion.\n" +
                        "• Penetration pricing: a low price to enter a market, raised later.\n" +
                        "• Market segmentation: dividing a market into groups of customers with similar needs (age, income, location).",
                keyPoints = listOf(
                    "Sole trader = unlimited liability",
                    "Ltd = limited liability",
                    "4Ps: Product, Price, Place, Promotion",
                    "Maslow starts with physiological needs"
                ),
                imageRes = R.drawable.ill_business_marketing,
                videoUrl = yt("IGCSE business studies 0450 marketing mix limited liability"),
                practice = Question("Which business activity is in the tertiary sector?", listOf("Banking", "Fishing", "Car manufacturing", "Coal mining"), 0, "Tertiary = services, such as banking.")
            ),
            Lesson(
                id = "bus_finance", subjectId = "business",
                title = "Finance & Operations",
                summary = "Break-even, profit, ratios and methods of production.",
                content = "Costs: fixed costs (rent, salaries) don't change with output. Variable costs (materials) rise with output.\n\n" +
                        "Break-even output = fixed costs ÷ (price − variable cost per unit).\n" +
                        "Example: $12 000 ÷ ($10 − $6) = 3000 units.\n\n" +
                        "Profit:\n" +
                        "• Gross profit = revenue − cost of sales\n" +
                        "• Profit for the year = gross profit − expenses\n\n" +
                        "Liquidity: current ratio = current assets ÷ current liabilities. $60 000 ÷ $40 000 = 1.5.\n\n" +
                        "Methods of production:\n" +
                        "• Job: one-off products made to order (e.g. a wedding dress)\n" +
                        "• Batch: groups of identical products (e.g. bread)\n" +
                        "• Flow: continuous mass production (e.g. soft drinks)",
                keyPoints = listOf(
                    "Break-even = FC ÷ (price − VC per unit)",
                    "Gross profit = revenue − cost of sales",
                    "Current ratio = CA ÷ CL",
                    "Job / batch / flow production"
                ),
                imageRes = R.drawable.ill_business_finance,
                videoUrl = yt("IGCSE business studies 0450 break even current ratio"),
                practice = Question("A product sells for $25 and has variable costs of $15 per unit. What is the contribution per unit?", listOf("$10", "$40", "$15", "$25"), 0, "Contribution = price − variable cost = $25 − $15 = $10.")
            )
        ),
        questions = listOf(
            Question("Which is a feature of a sole trader?", listOf("Unlimited liability", "Limited liability", "Owned by shareholders", "Shares sold on the stock exchange"), 0, "The owner is personally responsible for all the debts."),
            Question("What is the main advantage of a private limited company for its owners?", listOf("Limited liability", "No need to share profits", "Shares can be sold to the public", "No legal formalities"), 0, "Shareholders can only lose the money they invested."),
            Question("Which is an example of a primary sector business?", listOf("A fishing company", "A car factory", "A bank", "A hotel"), 0, "The primary sector extracts natural resources."),
            Question("Which is NOT one of the 4Ps of the marketing mix?", listOf("People", "Product", "Place", "Promotion"), 0, "The 4Ps are Product, Price, Place and Promotion."),
            Question("What is market segmentation?", listOf("Dividing a market into groups of customers with similar characteristics", "Selling products in many countries", "Setting a low price to enter a market", "Researching competitors' prices"), 0, "Segments can be based on age, income, gender, location and so on."),
            Question("A firm sets a low price for a new product to gain market share, then raises it later. What is this pricing method?", listOf("Penetration pricing", "Price skimming", "Cost-plus pricing", "Competitive pricing"), 0, "Skimming is the opposite: high price first, then lower."),
            Question("Fixed costs are $12 000, price is $10 and variable cost is $6 per unit. What is the break-even output?", listOf("3000 units", "1200 units", "2000 units", "750 units"), 0, "12 000 ÷ (10 − 6) = 3000 units."),
            Question("Revenue is $50 000 and cost of sales is $30 000. What is gross profit?", listOf("$20 000", "$80 000", "$30 000", "$1.67"), 0, "Gross profit = revenue − cost of sales."),
            Question("Current assets are $60 000 and current liabilities are $40 000. What is the current ratio?", listOf("1.5", "0.67", "20 000", "2.5"), 0, "60 000 ÷ 40 000 = 1.5."),
            Question("Which method of production is used to make a single, unique wedding dress?", listOf("Job production", "Batch production", "Flow production", "Mass production"), 0, "Job production makes one-off items to order."),
            Question("According to Maslow, which need must be met first?", listOf("Physiological needs", "Esteem needs", "Social needs", "Self-actualisation"), 0, "Food, water and shelter are at the base of the hierarchy."),
            Question("Which is an INTERNAL stakeholder?", listOf("Employees", "Customers", "Suppliers", "The government"), 0, "Internal stakeholders work in or own the business."),
            Question("Which is a fixed cost for a bakery?", listOf("Rent of the shop", "Flour", "Packaging", "Electricity used by the ovens"), 0, "Rent stays the same however much bread is made.")
        )
    )

    // 9. ECONOMICS
    private val economics = Subject(
        "economics", "Economics", "IGCSE 0455 · markets, money and government",
        R.color.economics, R.color.economics_light, R.drawable.ic_economics,
        lessons = listOf(
            Lesson(
                id = "econ_markets", subjectId = "economics",
                title = "Markets: Demand, Supply & Costs",
                summary = "Scarcity, opportunity cost, elasticity, externalities and costs.",
                content = "The basic economic problem: SCARCITY arises because wants are unlimited but resources are limited.\n\n" +
                        "Opportunity cost is the next best alternative given up. Example: spending $100m on wind farms instead of healthcare.\n\n" +
                        "Demand: higher consumer incomes increase demand for normal goods, which raises the price.\n\n" +
                        "Price elasticity of demand (PED): if demand is relatively INELASTIC, raising the price increases total revenue.\n\n" +
                        "External benefits are gains to third parties. More graduates → a more productive workforce → more tax revenue.\n\n" +
                        "Firms:\n" +
                        "• Revenue = money received from sales.\n" +
                        "• Fixed costs (e.g. office rent) don't change with output; variable costs (fuel, raw materials) do.\n" +
                        "• Internal diseconomies of scale: costs per unit rise as a firm grows too big, e.g. slower decision-making after a merger.",
                keyPoints = listOf(
                    "Scarcity = unlimited wants, limited resources",
                    "Opportunity cost = next best alternative",
                    "Inelastic demand: price ↑ → revenue ↑",
                    "Rent is a fixed cost"
                ),
                imageRes = R.drawable.ill_economics_markets,
                videoUrl = yt("IGCSE economics 0455 price elasticity of demand opportunity cost"),
                practice = Question("A student spends an evening studying instead of working a paid shift. What is the opportunity cost?", listOf("The wages from the shift", "The cost of textbooks", "The exam fee", "Nothing"), 0, "Opportunity cost is the next best alternative given up.")
            ),
            Lesson(
                id = "econ_macro", subjectId = "economics",
                title = "Money, Government & Trade",
                summary = "Functions of money, inflation, subsidies, trade and exchange rates.",
                content = "Functions of money: medium of exchange, measure (unit) of value, store of value and standard of deferred payment.\n" +
                        "Inflation reduces money's value as a STORE OF VALUE, which hurts savers.\n\n" +
                        "Who gains from inflation? People repaying borrowed money, because the real value of their debt falls.\n\n" +
                        "Government policy:\n" +
                        "• Subsidies increase the consumption of MERIT goods (e.g. education, healthcare).\n" +
                        "• A maximum price is a form of regulation.\n" +
                        "• Supply-side policies (e.g. training) increase economic growth.\n\n" +
                        "International trade:\n" +
                        "• Balance of trade in goods and services = (exports of goods + services) − (imports of goods + services).\n" +
                        "• More exports → foreigners need the currency → demand for the currency rises.",
                keyPoints = listOf(
                    "Inflation hurts money's store of value",
                    "Borrowers gain from inflation",
                    "Subsidies encourage merit goods",
                    "More exports → higher demand for the currency"
                ),
                imageRes = R.drawable.ill_economics_macro,
                videoUrl = yt("IGCSE economics 0455 functions of money inflation balance of payments"),
                practice = Question("Exports are $500m and imports are $450m. What is the trade balance?", listOf("A surplus of $50m", "A deficit of $50m", "A surplus of $950m", "A deficit of $950m"), 0, "500 − 450 = +50, so a surplus.")
            )
        ),
        questions = listOf(
            Question("What is part of the economic definition of scarcity?", listOf("High levels of taxation", "Low levels of investment", "Unequal distribution of income", "Unlimited consumer wants"), 3, "Scarcity = unlimited wants but limited resources. ($ECONOMICS, Q1)"),
            Question("A government spends \$100m on wind farms instead of more healthcare. Which concept does this show?", listOf("External cost", "Free goods", "Market disequilibrium", "Opportunity cost"), 3, "The healthcare given up is the opportunity cost. ($ECONOMICS, Q3)"),
            Question("Which PED would cause total revenue to INCREASE if the price rises?", listOf("Perfectly elastic", "Relatively elastic", "Relatively inelastic", "Unitary elastic"), 2, "Quantity falls by a smaller percentage than the price rises. ($ECONOMICS, Q6)"),
            Question("What might be an EXTERNAL benefit of more university graduates?", listOf("More fee revenue for universities", "More tax revenue from a more productive workforce", "Higher graduate employment", "Higher wages for graduates"), 1, "The benefit goes to third parties (society), not the graduates or universities. ($ECONOMICS, Q8)"),
            Question("The price of a good increases. What could cause this?", listOf("The cost of raw materials falls.", "Consumers' incomes rise.", "The price of a complement rises.", "The price of a substitute falls."), 1, "Higher incomes increase demand, pushing the price up. ($ECONOMICS, Q9)"),
            Question("Savers suffer as inflation rises to 4.4%. Which function of money is most involved?", listOf("Standard of deferred payment", "Medium of exchange", "Store of value", "Unit of account"), 2, "Inflation reduces what saved money can buy. ($ECONOMICS, Q11)"),
            Question("What is NOT a variable cost of production?", listOf("Cost of fuel", "Cost of raw materials", "Overtime pay", "Rent of offices"), 3, "Rent is fixed; it does not change with output. ($ECONOMICS, Q14)"),
            Question("What could cause INTERNAL diseconomies of scale for a firm?", listOf("A fall in demand for its products", "A merger that slows down decision-making", "More spending on research and development", "A lack of skilled labour in the area"), 1, "Poor communication and slow decisions in a large firm raise average costs. ($ECONOMICS, Q16)"),
            Question("What is the most likely reason to increase government subsidies?", listOf("To decrease consumption of a merit good", "To increase consumption of a merit good", "To decrease consumption of a demerit good", "To increase consumption of a demerit good"), 1, "Subsidies lower prices and encourage consumption of merit goods. ($ECONOMICS, Q18)"),
            Question("Which people are most likely to GAIN during inflation?", listOf("Companies that sell abroad", "Foreign tourists visiting the country", "People living on their savings", "People repaying borrowed money"), 3, "The real value of their debt falls. ($ECONOMICS, Q19)"),
            Question("Exports of goods \$200m, imports of goods \$190m, exports of services \$35m, imports of services \$38m. What is the balance of trade in goods and services?", listOf("A deficit of \$7m", "A deficit of \$13m", "A surplus of \$7m", "A surplus of \$13m"), 2, "(200 + 35) − (190 + 38) = +7, a surplus. ($ECONOMICS, Q27)"),
            Question("What would increase the demand for a country's currency?", listOf("Less inward investment", "A lower interest rate", "More exports", "More imports"), 2, "Foreign buyers need the currency to pay for exports. ($ECONOMICS, Q29)")
        )
    )

    // 10. GEOGRAPHY
    private val geography = Subject(
        "geography", "Geography", "IGCSE 0460 · tectonics and population",
        R.color.geography, R.color.geography_light, R.drawable.ic_globe,
        lessons = listOf(
            Lesson(
                id = "geo_tectonics", subjectId = "geography",
                title = "Plate Tectonics & Hazards",
                summary = "Plate boundaries, volcanoes, earthquakes and living with hazards.",
                content = "Earth's crust is broken into tectonic plates that move on convection currents in the mantle.\n\n" +
                        "Plate boundaries:\n" +
                        "• Constructive – plates move apart. Runny magma gives gentle eruptions.\n" +
                        "• Destructive – the denser oceanic plate is subducted under a continental plate. Viscous, gas-rich magma gives explosive eruptions.\n" +
                        "• Conservative – plates slide past each other. Earthquakes only.\n" +
                        "• Collision – two continental plates meet and form fold mountains (e.g. the Himalayas).\n\n" +
                        "An earthquake starts at the focus. The point on the surface above it is the epicentre.\n\n" +
                        "Why people live near volcanoes: fertile soils, geothermal energy, minerals and tourism.\n\n" +
                        "Prediction: tiltmeters detect swelling, seismometers record tremors, and scientists monitor gas emissions.",
                keyPoints = listOf(
                    "Destructive: subduction → explosive eruptions",
                    "Conservative: earthquakes, no volcanoes",
                    "Collision: fold mountains",
                    "Epicentre = surface point above the focus"
                ),
                imageRes = R.drawable.ill_geography_tectonics,
                videoUrl = yt("IGCSE geography 0460 plate boundaries volcanoes earthquakes"),
                practice = Question("At which boundary is an oceanic plate subducted under a continental plate?", listOf("Constructive", "Destructive", "Conservative", "Collision"), 1, "The denser oceanic plate sinks at a destructive boundary.")
            ),
            Lesson(
                id = "geo_population", subjectId = "geography",
                title = "Population & Settlement",
                summary = "Natural increase, population pyramids, migration and urbanisation.",
                content = "Natural increase = birth rate − death rate. With a birth rate of 20 per 1000 and a death rate of 8 per 1000, it is 12 per 1000 (1.2%).\n\n" +
                        "Demographic Transition Model: in Stage 2 the death rate falls quickly (better healthcare, clean water) while the birth rate stays high, so the population grows rapidly.\n\n" +
                        "Population pyramids: a wide base shows a high birth rate (typical of LICs). A narrow base and wide top show an ageing population (typical of HICs).\n\n" +
                        "Dependent population: people aged under 15 and over 64.\n\n" +
                        "Migration:\n" +
                        "• Push factors (e.g. a lack of jobs, drought) make people leave an area.\n" +
                        "• Pull factors (e.g. better services, jobs) attract them.\n\n" +
                        "Urbanisation is the increase in the PROPORTION of people living in towns and cities.",
                keyPoints = listOf(
                    "Natural increase = birth rate − death rate",
                    "DTM Stage 2: death rate falls, birth rate high",
                    "Dependants: under 15 and over 64",
                    "Push factors make people leave"
                ),
                imageRes = R.drawable.ill_geography_population,
                videoUrl = yt("IGCSE geography 0460 population pyramids demographic transition model"),
                practice = Question("Which is a PULL factor for migration to a city?", listOf("Better healthcare and schools", "Drought in rural areas", "Civil war", "Lack of jobs in villages"), 0, "Pull factors attract people to a place.")
            )
        ),
        questions = listOf(
            Question("Why are eruptions at destructive boundaries often more explosive than at constructive ones?", listOf("The magma is more viscous and gas-rich", "The plates move apart faster", "The magma is runnier and hotter", "There is no crust above the magma"), 0, "Melted oceanic crust forms thick, gas-rich magma, and pressure builds up."),
            Question("Which method helps scientists predict a volcanic eruption?", listOf("Tiltmeters detecting the ground swelling", "The Richter scale after the eruption", "Counting tourists", "Measuring annual rainfall"), 0, "Rising magma makes the volcano swell."),
            Question("Which is a main reason people continue to live near active volcanoes?", listOf("Fertile soils for farming", "Volcanoes prevent earthquakes", "The land has no risk", "The climate is always cooler"), 0, "Volcanic ash weathers into fertile soil."),
            Question("Which hazard happens at a conservative plate boundary?", listOf("Earthquakes only", "Volcanoes only", "Both", "Neither"), 0, "Plates slide past each other; no magma rises."),
            Question("Which type of boundary formed the Himalayas?", listOf("Collision", "Constructive", "Conservative", "Hotspot"), 0, "Two continental plates pushed together and folded upwards."),
            Question("What is the epicentre of an earthquake?", listOf("The point on the surface directly above the focus", "The point underground where it starts", "The crack in the rock", "The top of a volcano"), 0, "The focus is underground; the epicentre is above it."),
            Question("A country's birth rate is 20 per 1000 and its death rate is 8 per 1000. What is the natural increase?", listOf("1.2%", "2.8%", "12%", "0.12%"), 0, "20 − 8 = 12 per 1000 = 1.2%."),
            Question("A population pyramid has a very wide base. What does this show?", listOf("A high birth rate", "An ageing population", "A low birth rate", "A high life expectancy"), 0, "Many young children → high birth rate, typical of LICs."),
            Question("In which stage of the Demographic Transition Model does the death rate fall rapidly while the birth rate stays high?", listOf("Stage 2", "Stage 1", "Stage 4", "Stage 5"), 0, "Better healthcare lowers deaths before birth rates fall."),
            Question("Which group makes up the dependent population?", listOf("People aged under 15 and over 64", "People aged 15–64", "Unemployed people only", "Migrants"), 0, "These age groups depend on the working population."),
            Question("Which is a PUSH factor for rural–urban migration?", listOf("A lack of jobs in the countryside", "Better hospitals in the city", "Higher wages in the city", "Bright lights and entertainment"), 0, "Push factors make people want to leave."),
            Question("What is urbanisation?", listOf("An increase in the proportion of people living in urban areas", "People moving from cities to villages", "The growth of farms", "Building new roads"), 0, "It is about the proportion living in towns and cities.")
        )
    )

    // 11. HISTORY
    private val history = Subject(
        "history", "History", "IGCSE 0470 · international relations 1919–1989",
        R.color.history, R.color.history_light, R.drawable.ic_history,
        lessons = listOf(
            Lesson(
                id = "hist_league", subjectId = "history",
                title = "Peace Treaties & the League of Nations",
                summary = "The Treaty of Versailles and why the League of Nations failed.",
                content = "Treaty of Versailles (signed June 1919):\n" +
                        "• Article 231, the War Guilt Clause: Germany accepted blame for the war.\n" +
                        "• Reparations: £6600 million (set in 1921).\n" +
                        "• The German army was limited to 100 000 men, with no air force; the Rhineland was demilitarised.\n" +
                        "• Germany lost territory, including Alsace-Lorraine, and all its colonies.\n\n" +
                        "The League of Nations (1920) aimed to keep peace through collective security.\n" +
                        "Weaknesses:\n" +
                        "• The USA never joined.\n" +
                        "• It had no army of its own.\n" +
                        "• Decisions had to be unanimous.\n\n" +
                        "Failures:\n" +
                        "• Manchuria (1931): Japan invaded Manchuria in China, then left the League.\n" +
                        "• Abyssinia (1935): Italy invaded Abyssinia (Ethiopia). Sanctions did not include oil.",
                keyPoints = listOf(
                    "Article 231 = War Guilt Clause",
                    "German army limited to 100 000",
                    "USA never joined the League",
                    "Manchuria 1931 (Japan), Abyssinia 1935 (Italy)"
                ),
                imageRes = R.drawable.ill_history_league,
                videoUrl = yt("IGCSE history 0470 Treaty of Versailles League of Nations"),
                practice = Question("Which treaty ended the First World War with Germany?", listOf("Treaty of Versailles", "Treaty of Trianon", "Treaty of Sèvres", "Treaty of Brest-Litovsk"), 0, "Germany signed the Treaty of Versailles in June 1919.")
            ),
            Lesson(
                id = "hist_coldwar", subjectId = "history",
                title = "Road to War & the Cold War",
                summary = "Hitler's foreign policy, then superpower rivalry 1945–1989.",
                content = "Road to the Second World War:\n" +
                        "• Anschluss (March 1938): union of Germany and Austria.\n" +
                        "• Munich Agreement (September 1938): Britain and France let Germany take the Sudetenland (appeasement).\n" +
                        "• Nazi–Soviet Pact (August 1939): Germany and the USSR agreed not to fight each other.\n" +
                        "• Germany invaded Poland on 1 September 1939.\n\n" +
                        "The Cold War:\n" +
                        "• Truman Doctrine (1947): the USA would contain the spread of communism.\n" +
                        "• Berlin Blockade (1948–49): the West responded with the Berlin Airlift.\n" +
                        "• Berlin Wall built in 1961.\n" +
                        "• Cuban Missile Crisis (October 1962): Soviet missiles in Cuba; ended when the USSR removed them.\n" +
                        "• The Berlin Wall fell in 1989.",
                keyPoints = listOf(
                    "Anschluss & Munich: 1938",
                    "Nazi–Soviet Pact: August 1939",
                    "Truman Doctrine = containment",
                    "Wall: built 1961, fell 1989"
                ),
                imageRes = R.drawable.ill_history_coldwar,
                videoUrl = yt("IGCSE history 0470 Munich agreement Cold War Cuban missile crisis"),
                practice = Question("What was appeasement?", listOf("Giving in to Hitler's demands to avoid war", "Declaring war on Germany", "An alliance of Germany and Italy", "The rebuilding of Europe after 1945"), 0, "Britain and France hoped that meeting Hitler's demands would prevent war.")
            )
        ),
        questions = listOf(
            Question("In which year was the Treaty of Versailles signed?", listOf("1919", "1918", "1920", "1923"), 0, "It was signed on 28 June 1919."),
            Question("What was Article 231 of the Treaty of Versailles?", listOf("The War Guilt Clause", "The creation of the League of Nations", "The demilitarisation of the Rhineland", "The return of Alsace-Lorraine"), 0, "Germany had to accept the blame for the war."),
            Question("What was the German army limited to by the Treaty of Versailles?", listOf("100 000 men", "1 million men", "50 000 men", "No limit"), 0, "Germany was also banned from having an air force."),
            Question("Which major power never joined the League of Nations?", listOf("USA", "Britain", "France", "Italy"), 0, "The US Senate refused to join, weakening the League."),
            Question("Which country invaded Manchuria in 1931?", listOf("Japan", "Italy", "Germany", "USSR"), 0, "The League's slow response showed its weakness."),
            Question("Which country invaded Abyssinia in 1935?", listOf("Italy", "Japan", "Germany", "Britain"), 0, "Mussolini's invasion; League sanctions did not include oil."),
            Question("What was the Anschluss of 1938?", listOf("The union of Germany and Austria", "The German takeover of the Sudetenland", "A pact between Germany and the USSR", "The invasion of Poland"), 0, "It broke the Treaty of Versailles."),
            Question("What did the Munich Agreement of September 1938 give Germany?", listOf("The Sudetenland", "Austria", "Poland", "The Rhineland"), 0, "It is the best-known example of appeasement."),
            Question("When was the Nazi–Soviet Pact signed?", listOf("August 1939", "September 1938", "June 1941", "March 1936"), 0, "It cleared the way for Germany to invade Poland."),
            Question("How did the West respond to the Berlin Blockade (1948–49)?", listOf("The Berlin Airlift", "Building the Berlin Wall", "Invading East Germany", "Leaving Berlin"), 0, "Supplies were flown into West Berlin for almost a year."),
            Question("In which year did the Cuban Missile Crisis take place?", listOf("1962", "1961", "1956", "1968"), 0, "October 1962: thirteen days that brought the world close to nuclear war."),
            Question("What was the aim of the Truman Doctrine (1947)?", listOf("To contain the spread of communism", "To rebuild Germany's army", "To end the League of Nations", "To withdraw the USA from Europe"), 0, "The USA promised support to countries resisting communism."),
            Question("In which year did the Berlin Wall fall?", listOf("1989", "1961", "1991", "1985"), 0, "It was built in 1961 and opened on 9 November 1989.")
        )
    )

    // 12. ACCOUNTING
    private val accounting = Subject(
        "accounting", "Accounting", "IGCSE 0452 · double entry, statements and ratios",
        R.color.accounting, R.color.accounting_light, R.drawable.ic_accounting,
        lessons = listOf(
            Lesson(
                id = "acc_basics", subjectId = "accounting",
                title = "Double Entry & Financial Statements",
                summary = "Book-keeping, the accounting equation, errors, depreciation and inventory.",
                content = "Book-keeping is RECORDING financial transactions. Accounting then summarises, communicates and interprets them.\n\n" +
                        "The accounting equation: Assets = Capital + Liabilities.\n" +
                        "• Capital: finance provided by the owner.\n" +
                        "• Liabilities: amounts owed (trade payables, unpaid expenses, loans).\n" +
                        "• Assets: things owned (property, trade receivables, inventory, bank).\n" +
                        "The statement of financial position shows assets and liabilities at a certain DATE.\n\n" +
                        "Errors not shown by a trial balance include commission (right type of account, wrong person, e.g. J Sharp vs T Sharpe), principle, omission, original entry and complete reversal.\n\n" +
                        "Depreciation:\n" +
                        "• Straight-line: the same amount each year, e.g. 25% × $15 000 = $3750.\n" +
                        "• Reducing balance: a percentage of the net book value each year.\n\n" +
                        "Inventory is valued at the LOWER of cost and net realisable value.\n\n" +
                        "Accruals: match expenses to the year they relate to, even if paid in a different year.\n\n" +
                        "Money measurement principle: only items that can be measured in money are recorded.",
                keyPoints = listOf(
                    "Assets = Capital + Liabilities",
                    "Error of commission = wrong person's account",
                    "Inventory: lower of cost and NRV",
                    "Money measurement: only record money values"
                ),
                imageRes = R.drawable.ill_accounting_basics,
                videoUrl = yt("IGCSE accounting 0452 double entry depreciation errors"),
                practice = Question("A business has assets of \$50 000 and liabilities of \$20 000. What is its capital?", listOf("\$30 000", "\$70 000", "\$20 000", "\$50 000"), 0, "Capital = Assets − Liabilities = 50 000 − 20 000.")
            ),
            Lesson(
                id = "acc_analysis", subjectId = "accounting",
                title = "Partnerships, Clubs & Ratios",
                summary = "Control accounts, partnerships, clubs, incomplete records and ROCE.",
                content = "Sales ledger control account: closing balance = opening balance + credit sales − receipts − irrecoverable debts.\n\n" +
                        "Partnerships: the appropriation account ADDS interest on drawings to the profit, then deducts salaries and interest on capital. The rest is shared in the profit-sharing ratio.\n\n" +
                        "Clubs: a separate account shows the profit on an event. Example: tickets $1500 + programmes $150 − prizes $450 − judge's fee $250 = $950.\n\n" +
                        "Manufacturing: cost of raw materials consumed = opening inventory + purchases + carriage inwards − closing inventory.\n\n" +
                        "Incomplete records: profit = closing capital − opening capital + drawings (− any capital introduced).\n\n" +
                        "Ratios:\n" +
                        "• Return on capital employed (ROCE) = profit before interest ÷ (equity + non-current liabilities) × 100.\n" +
                        "• Current ratio = current assets ÷ current liabilities.\n" +
                        "• Liquid (acid test) ratio = (current assets − inventory) ÷ current liabilities.",
                keyPoints = listOf(
                    "Interest on drawings is ADDED in the appropriation account",
                    "Raw materials = opening + purchases + carriage in − closing",
                    "Profit = closing − opening capital + drawings",
                    "ROCE = profit before interest ÷ capital employed"
                ),
                imageRes = R.drawable.ill_accounting_analysis,
                videoUrl = yt("IGCSE accounting 0452 partnership appropriation ROCE incomplete records"),
                practice = Question("Current assets are \$30 000 and current liabilities are \$15 000. What is the current ratio?", listOf("2 : 1", "0.5 : 1", "15 000 : 1", "1.5 : 1"), 0, "30 000 ÷ 15 000 = 2, so the ratio is 2 : 1.")
            )
        ),
        questions = listOf(
            Question("What is the main function of book-keeping?", listOf("Communicating information", "Interpreting information", "Recording information", "Summarising information"), 2, "Book-keeping records transactions; accounting does the rest. ($ACCOUNTING, Q1)"),
            Question("Which components match: (1) finance provided by the owner, (2) amounts owed by the business, (3) property, receivables, inventory and bank?", listOf("Assets, capital, liabilities", "Assets, liabilities, capital", "Capital, assets, liabilities", "Capital, liabilities, assets"), 3, "Owner's finance = capital; owed = liabilities; owned = assets. ($ACCOUNTING, Q2)"),
            Question("Goods sold on credit to J Sharp were debited in error to T Sharpe's account. Which type of error is this?", listOf("Commission", "Complete reversal", "Original entry", "Principle"), 0, "The right type of account, but the wrong person's. ($ACCOUNTING, Q8)"),
            Question("A vehicle costing \$15 000 should be depreciated at 25% straight-line, but 15% was used. How will correcting this affect profit?", listOf("Decrease \$1500", "Decrease \$2250", "Increase \$1500", "Increase \$2250"), 0, "25% = \$3750 vs 15% = \$2250, so \$1500 more expense. ($ACCOUNTING, Q10)"),
            Question("Opening sales ledger balance \$1800 (debit). Irrecoverable debt written off \$200, cash received \$10 000, closing balance \$3000. What were the credit sales?", listOf("\$7200", "\$11 200", "\$11 400", "\$11 600"), 2, "3000 = 1800 + sales − 10 000 − 200 → sales = \$11 400. ($ACCOUNTING, Q13)"),
            Question("Product P: 200 units, cost \$2.50, selling price \$2.00. Product J: 300 units, cost \$3.00, selling price \$3.50. What is the inventory value?", listOf("\$1300", "\$1400", "\$1450", "\$1550"), 0, "Lower of cost and NRV: 200 × 2.00 + 300 × 3.00 = \$1300. ($ACCOUNTING, Q19)"),
            Question("What does the statement of financial position show?", listOf("Assets and liabilities at a certain date", "The annual profit or loss", "Changes in equity during the year", "Income and expenditure for the year"), 0, "It is a snapshot on one date. ($ACCOUNTING, Q21)"),
            Question("Insurance is paid each 1 January for 12 months: \$1680 in 2022 and \$1920 in 2023. What is the insurance expense for the year ended 31 March 2023?", listOf("\$1680", "\$1740", "\$1920", "\$2160"), 1, "9/12 × 1680 + 3/12 × 1920 = 1260 + 480 = \$1740. ($ACCOUNTING, Q22)"),
            Question("Erni's current account opened at \$1400 DEBIT. He gets a \$3000 salary and half of the residual profit. Profit was \$12 600. What is his closing CREDIT balance?", listOf("\$400", "\$3200", "\$6400", "\$9200"), 2, "Residual = (12 600 − 3000) ÷ 2 = 4800. −1400 + 3000 + 4800 = \$6400. ($ACCOUNTING, Q24)"),
            Question("Concert: tickets \$1500, programmes \$150, prizes \$450, judge's fee \$250. What was the profit?", listOf("\$950", "\$1050", "\$1450", "\$2100"), 0, "1500 + 150 − 450 − 250 = \$950. ($ACCOUNTING, Q28)"),
            Question("Opening raw materials \$50 000, closing \$60 000, purchases \$80 000, carriage inwards \$2000. What was the cost of raw materials consumed?", listOf("\$70 000", "\$72 000", "\$90 000", "\$92 000"), 1, "50 000 + 80 000 + 2000 − 60 000 = \$72 000. ($ACCOUNTING, Q29)"),
            Question("Ashwin started with a \$5000 taxi and \$100 in the bank. At year end: bank \$1750, taxi \$4000, owes \$50. Drawings were \$1600. What was his profit?", listOf("\$1650", "\$2200", "\$2250", "\$3250"), 1, "Closing capital 5700 − opening 5100 + drawings 1600 = \$2200. ($ACCOUNTING, Q30)"),
            Question("Profit before interest \$29 200, equity \$192 000, 6% debentures \$20 000. What is the ROCE?", listOf("13.21%", "13.77%", "14.58%", "15.21%"), 1, "29 200 ÷ 212 000 × 100 = 13.77%. ($ACCOUNTING, Q32)"),
            Question("A café owner makes no entry for increased competition from a new café nearby. Which principle is this?", listOf("Historic cost", "Materiality", "Money measurement", "Prudence"), 2, "Competition can't be measured in money, so it isn't recorded. ($ACCOUNTING, Q35)")
        )
    )

    // Public API
    val subjects: List<Subject> = listOf(
        maths, biology, chemistry, physics, computerScience, ict,
        english, business, economics, accounting, geography, history
    )

    val totalLessons: Int get() = subjects.sumOf { it.lessons.size }

    fun subject(id: String?): Subject = subjects.firstOrNull { it.id == id } ?: subjects.first()

    fun lesson(id: String?): Lesson? = subjects.flatMap { it.lessons }.firstOrNull { it.id == id }

    val funFacts = listOf(
        "Honey never spoils — archaeologists found 3,000-year-old honey that was still edible!",
        "A day on Venus is longer than a year on Venus.",
        "Zero was developed as a number by Indian mathematicians around the 5th century.",
        "The first computer \"bug\" was a real moth found in a computer in 1947.",
        "Octopuses have three hearts and blue blood.",
        "The Berlin Wall stood for 28 years, from 1961 to 1989.",
        "The word \"robot\" comes from the Czech word \"robota\", meaning forced labour."
    )
}
