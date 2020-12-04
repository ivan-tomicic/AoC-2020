import java.util.*;

public class Day4 {

    public static void main(String[] args) {

        List<String> passportFields = new ArrayList<>(Arrays.asList("byr","ecl", "eyr", "hcl", "hgt", "iyr", "pid"));
        List<String> regexes = new ArrayList<>(Arrays.asList("^[0-9]{4}$", "^(amb|blu|brn|gry|grn|hzl|oth)$", "^[0-9]{4}$", "^#[0-9a-f]{6}$", "^[0-9]*(cm|in)$", "^[0-9]{4}$", "^[0-9]{9}$"));
        Map<String, String> currentPassportFields = new LinkedHashMap<>();

        Scanner sc = new Scanner(System.in);

        int validPassports1 = 0;
        int validPassports2 = 0;

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.strip() == "" || !sc.hasNextLine()) {
                if (currentPassportFields.keySet().contains("cid")) currentPassportFields.remove("cid");
                if(currentPassportFields.keySet().size() == 7) {
                    validPassports1++;
                    boolean passportValid2 = true;
                    //"byr","ecl", "eyr", "hcl", "hgt", "iyr", "pid"
                    String byr = currentPassportFields.get("byr");
                    String ecl = currentPassportFields.get("ecl");
                    String eyr = currentPassportFields.get("eyr");
                    String hcl = currentPassportFields.get("hcl");
                    String hgt = currentPassportFields.get("hgt");
                    String iyr = currentPassportFields.get("iyr");
                    String pid = currentPassportFields.get("pid");
                    if(!byr.matches(regexes.get(0)) || Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) passportValid2 = false;
                    if(!ecl.matches(regexes.get(1))) passportValid2 = false;
                    if(!eyr.matches(regexes.get(2)) || Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) passportValid2 = false;
                    if(!hcl.matches(regexes.get(3))) passportValid2 = false;
                    if(!hgt.matches(regexes.get(4))) passportValid2 = false;
                    if(hgt.contains("cm") && (Integer.parseInt(hgt.split("cm")[0]) > 193 || Integer.parseInt(hgt.split("cm")[0]) < 150)) passportValid2 = false;
                    if(hgt.contains("in") && (Integer.parseInt(hgt.split("in")[0]) > 76 || Integer.parseInt(hgt.split("in")[0]) < 59)) passportValid2 = false;
                    if(!iyr.matches(regexes.get(5)) || Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) passportValid2 = false;
                    if(!pid.matches(regexes.get(6))) passportValid2 = false;

                    if(passportValid2) validPassports2++;
                }
                System.out.println(currentPassportFields);
                currentPassportFields = new LinkedHashMap<>();

            }
            else {
                String[] fields = line.split("\\s+");
                for(String s : fields) {
                    currentPassportFields.put(s.split(":")[0], s.split(":")[1]);
                }
            }

        }
        System.out.println(validPassports1);
        System.out.println(validPassports2);

    }
}
