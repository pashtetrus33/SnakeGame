package tasks_with_arrays;

public class Main {
    public static void main(String[] args) {
        int[] test1 = {2, 1, 2, 3, 4};
        int[] test2 = {5, 6, 4, 5, 0, 0, 5, 6};
        int[] test3 = {1, 3, 5};
        final int[] TESTARRAY = test2;

        System.out.println(countEvens(TESTARRAY));

        System.out.println(minMaxStep(TESTARRAY));

        System.out.println(isTwoZerosNextTo(TESTARRAY));
    }


    /**
     * �����, ������������ ���������� ������ ��������� �������. countEvens([2, 1, 2, 3, 4]) ? 3 countEvens([2, 2, 0]) ? 3 countEvens([1, 3, 5]) ?
     *
     * @param array ������� ������
     * @return ���������� ������ ��������� �������
     */
    private static int countEvens(int[] array) {
        int count = 0;
        for (int i : array) {
            //������ ����� - ������� �� ������� �� 2 = 0
            if (i % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * �������� �������, ������������ ������� ����� ����� ������� � ����� ��������� ���������� ����������� �� ������� �������.
     *
     * @param array ������� ������
     * @return max - min
     */
    private static int minMaxStep(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            } else if (i < min) {
                min = i;
            }
        }
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        return max - min;
    }

    /**
     * �������� �������, ������������ ������, ���� � ���������� ������� ���� ��� �������� ��������, � ������� ���������.
     *
     * @param array ������� ������
     * @return true ���� ��� �������� �������� ������� � �������
     */
    private static boolean isTwoZerosNextTo(int[] array) {
        int current = array[0];
        for (int i = 1; i < array.length; i++) {
            if ((current == 0) && (array[i] == 0)) {
                System.out.printf("������� �������: %d, %d\n", i - 1, i);
                return true;
            } else {
                current = array[i];
            }
        }
        return false;
    }
}
