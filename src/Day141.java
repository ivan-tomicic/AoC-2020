import java.util.*;


public class Day141 {

    static Map<Long, Long> memory;

    static Map<String, Map<Long, Long>> input = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String mask = sc.nextLine().split("\\s+")[2];
        input.put(mask, new LinkedHashMap<>());

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.startsWith("mask")) {
                mask = line.split("\\s+")[2];
                input.put(mask, new LinkedHashMap<>());
            } else {
                input.get(mask).put(Long.parseLong(line.split("\\[")[1].split("\\]")[0]),
                        Long.parseLong(line.split("\\s+")[2]));
            }
        }

        part1();
        part2();

    }

    public static void part1() {

        memory = new LinkedHashMap<>();

        for(String mask : input.keySet()) {
            for(Long position : input.get(mask).keySet()) {
                long value = input.get(mask).get(position);
                long changedNumber = 0;

                for(int i = 0; i < 36; i++) {
                    if(mask.charAt(35 - i) == '1') changedNumber += (1L << i);
                    else if(mask.charAt(35 - i) == 'X') changedNumber += value & (1L << i);
                }
                memory.put(position, changedNumber);
            }
        }

        long sum = 0;
        for(long value : memory.values()) sum += value;
        System.out.println(sum);

    }

    public static void part2() {
        memory = new LinkedHashMap<>();

        for(String mask : input.keySet()) {
            for(Long position : input.get(mask).keySet()) {
                Set<Long> allPossiblePositions = new LinkedHashSet<>();

                long changedPosition = position;
                for(int i = 0; i < 36; i++) {
                    if(mask.charAt(35 - i) == '1') changedPosition |= (1L << i);
                    if(mask.charAt(35 - i) == 'X') changedPosition -= (changedPosition & (1L << i));
                }

                allPossiblePositions.add(changedPosition);
                for(int i = 0; i < 36; i++) {
                    if(mask.charAt(35 - i) == 'X') {
                        Set<Long> temp = new LinkedHashSet<>();
                        for(long pos : allPossiblePositions) {
                            temp.add(pos);
                            temp.add(pos | (1L << i));
                        }
                        allPossiblePositions.addAll(temp);
                    }
                }

                for(long pos : allPossiblePositions) {
                    memory.put(pos, input.get(mask).get(position));
                }
            }
        }
        long sum = 0;
        for(long value : memory.values()) sum += value;
        System.out.println(sum);
    }
}
