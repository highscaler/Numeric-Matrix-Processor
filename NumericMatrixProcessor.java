import java.util.Objects;
import java.util.Scanner;

public class NumericMatrixProcessor {
    public static void main(String[] args) {
        Menu.startMenu();
    }
}

class Matrix {
    private double[][] transitSubMatrix;
    private double[][] subMatrix;

    private static double[][] scanMatrix(int n, int m, Scanner scanner) {
        double[][] matrix = new double[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public double[][] addMatrices(Scanner scanner) {
        System.out.print("Enter size of first matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter first matrix:");
        double[][] A = scanMatrix(n, m, scanner);

        System.out.print("Enter size of second matrix: > ");
        if (n == scanner.nextInt() && m == scanner.nextInt()) {
            System.out.println("Enter second matrix:");
            double[][] B = scanMatrix(n, m, scanner);

            double[][] C = new double[n][m];
            System.out.println("The addition result is:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            return C;
        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public double[][] multiplyMatrixToConstant(Scanner scanner, double[][] A, Double C) {
        if (scanner != null && A == null && C == null) {
            System.out.print("Enter size of a matrix: > ");
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println("Enter the matrix:");
            A = scanMatrix(n, m, scanner);
            System.out.print("Enter a constant: > ");
            C = scanner.nextDouble();
            System.out.println("The multiplication result is:");
        }

        double[][] B = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                B[i][j] = A[i][j] * C;
            }
        }
        return B;
    }

    public double[][] multiplyMatrices(Scanner scanner) {
        System.out.print("Enter size of first matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter first matrix:");
        double[][] A = scanMatrix(n, m, scanner);

        System.out.print("Enter size of second matrix: > ");
        if (m == scanner.nextInt()) {
            int k = scanner.nextInt();
            System.out.println("Enter second matrix:");
            double[][] B = scanMatrix(m, k, scanner);

            double[][] C = new double[n][k];
            System.out.println("The multiplication result is:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    for (int l = 0; l < m; l++) {
                        C[i][j] += A[i][l] * B[l][j];
                    }
                }
            }
            return C;
        } else {
            System.out.println("ERROR");
            return null;
        }
    }

    public double[][] transposeOverMainDiagonal(Scanner scanner, double[][] A) {
        if (scanner != null && A == null) {
            System.out.print("Enter size of a matrix: > ");
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            System.out.println("Enter the matrix:");
            A = scanMatrix(n, m, scanner);
            System.out.println("The result of transposition over the main diagonal is:");
        }

        for (int i = 0; i < A.length; i++) {
            for (int j = i; j < A[i].length; j++) {
                double temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
        return A;
    }

    public double[][] transposeOverSideDiagonal(Scanner scanner) {
        System.out.print("Enter size of a matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter the matrix:");
        double[][] A = scanMatrix(n, m, scanner);

        System.out.println("The result of transposition over the side diagonal is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m - i; j++) {
                double temp = A[i][j];
                A[i][j] = A[n - 1 - j][m - 1 - i];
                A[n - 1 - j][m - 1 - i] = temp;
            }
        }
        return A;
    }

    public double[][] transposeByVerticalLine(Scanner scanner) {
        System.out.print("Enter size of a matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter the matrix:");
        double[][] A = scanMatrix(n, m, scanner);

        System.out.println("The result of transposition by vertical line is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m / 2; j++) {
                double temp = A[i][j];
                A[i][j] = A[i][m - 1 - j];
                A[i][m - 1 - j] = temp;
            }
        }
        return A;
    }

    public double[][] transposeByHorizontalLine(Scanner scanner) {
        System.out.print("Enter size of a matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println("Enter the matrix:");
        double[][] A = scanMatrix(n, m, scanner);

        System.out.println("The result of transposition by horizontal line is:");
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                double temp = A[i][j];
                A[i][j] = A[n - 1 - i][j];
                A[n - 1 - i][j] = temp;
            }
        }
        return A;
    }

    public double getDeterminant(Scanner scanner) {
        System.out.print("Enter size of a matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        if (n != m) {
            System.out.println("The matrix determinant operation is only defined for square matrices!");
            return getDeterminant(scanner);}
        System.out.println("Enter the matrix:");
        double[][] A = scanMatrix(n, m, scanner);
        return calcDeterminant(A);
    }

    private double calcDeterminant(double[][] A) {
        double detA = 0;
        if (A.length == 1) {
            detA = 1;
            return detA;
        }
        if (A.length == 2) {
            detA = calcTwoOrderDet(A);
            return detA;
        }
        if (A.length == 3) {
            for (int i = 0; i < A.length; i++) {
                detA += A[0][i] * calcTwoOrderDet(calcSubMatrix(A, 0, i)) * Math.pow(-1, i);
            }
            return detA;
        }
        if (A.length > 3) {
            for (int i = 0; i < A.length; i++) {
                detA += A[0][i] * calcDeterminant(calcSubMatrix(A, 0, i)) * Math.pow(-1, i);
            }
        }
        return detA;
    }

    public double[][] getInverseMatrix(Scanner scanner) {
        System.out.print("Enter size of a matrix: > ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        if (n != m) {
            System.out.println("The matrix determinant operation is only defined for square matrices!");
            return getInverseMatrix(scanner);}
        System.out.println("Enter the matrix:");
        double[][] A = scanMatrix(n, m, scanner);
        double determinant = calcDeterminant(A);
        if (determinant == 0) {
            System.out.println("The matrix is degenerate and its inverse does not exist!");
            return getInverseMatrix(scanner);
        }
        double[][] unionMatrix = calcUnionMatrix(transposeOverMainDiagonal(null, A));
        System.out.println("The inversion result is:");
        return multiplyMatrixToConstant(null, unionMatrix, 1 / determinant);
    }

    private double[][] calcUnionMatrix(double[][] A) {
        double[][] B = new double[A.length][A.length];
        if (A.length == 1) {
            B[0][0] = 1;
            return B;
        }
        if (A.length == 2) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    B[i][j] = A[i][j] * Math.pow(-1, (i + j));
                }
            }
            return B;
        }
        if (A.length == 3) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    B[i][j] = calcTwoOrderDet(calcSubMatrix(A, i, j)) * Math.pow(-1, (i + j));
                }
            }
            return B;
        }
        if (A.length > 3) {
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A[i].length; j++) {
                    B[i][j] = calcDeterminant(calcSubMatrix(A, i, j)) * Math.pow(-1, (i + j));
                }
            }
        }
        return B;
    }

    private double calcTwoOrderDet(double[][] twoOrderMatrix) {
        return twoOrderMatrix[0][0] * twoOrderMatrix[1][1] - twoOrderMatrix[0][1] * twoOrderMatrix[1][0];
    }

    private double[][] calcSubMatrix(double[][] matrix, int rowForDelete, int columnForDelete) {
        deleteRow(matrix, rowForDelete);
        deleteColumn(transitSubMatrix, columnForDelete);
        return subMatrix;
    }

    private void deleteRow(double[][] matrix, int rowForDelete) {
        transitSubMatrix = new double[matrix.length - 1][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i < rowForDelete) {
                    transitSubMatrix[i][j] = matrix[i][j];
                }
                if (i > rowForDelete) {
                    transitSubMatrix[i - 1][j] = matrix[i][j];
                }
            }
        }
    }

    private void deleteColumn(double[][] transitSubMatrix, int columnForDelete) {
        subMatrix = new double[transitSubMatrix.length][transitSubMatrix.length];
        for (int i = 0; i < transitSubMatrix.length; i++) {
            for (int j = 0; j < transitSubMatrix.length + 1; j++) {
                if (j < columnForDelete) {
                    subMatrix[i][j] = transitSubMatrix[i][j];
                }
                if (j > columnForDelete) {
                    subMatrix[i][j - 1] = transitSubMatrix[i][j];
                }

            }
        }
    }
}

class Menu {
    static void startMenu() {
        Scanner scanner = new Scanner(System.in);
        Matrix matrix = new Matrix();
        boolean exit = false;

        while (!exit) {
            StateOfMenu choice = scanStateOfMenu(scanner, null);
            switch (choice) {
                case ADD_MATRICES -> printMatrix(Objects.requireNonNull(matrix.addMatrices(scanner)));
                case MULTIPLY_MATRIX_TO_A_CONSTANT -> printMatrix(matrix.multiplyMatrixToConstant(scanner, null, null));
                case MULTIPLY_MATRICES -> printMatrix(Objects.requireNonNull(matrix.multiplyMatrices(scanner)));
                case TRANSPOSE_MATRIX -> {
                    choice = scanStateOfMenu(scanner, choice);
                    switch (choice) {
                        case TRANSP_MAIN_DIAGONAL -> printMatrix(matrix.transposeOverMainDiagonal(scanner, null));
                        case TRANSP_SIDE_DIAGONAL -> printMatrix(matrix.transposeOverSideDiagonal(scanner));
                        case TRANSP_VERTICAL_LINE -> printMatrix(matrix.transposeByVerticalLine(scanner));
                        case TRANSP_HORIZONTAL_LINE -> printMatrix(matrix.transposeByHorizontalLine(scanner));
                    }
                }
                case DETERMINANT -> System.out.println(matrix.getDeterminant(scanner));
                case INVERSE_MATRIX -> printMatrix(matrix.getInverseMatrix(scanner));
                case EXIT -> exit = true;
            }
        }
    }

    static StateOfMenu scanStateOfMenu(Scanner scanner, StateOfMenu choice) {
        if (choice == null) {
            System.out.println();
            for (StateOfMenu value : StateOfMenu.values()) {
                if (value.getItemNumber() < 10) {
                    System.out.println(value.getItemName());
                }
            }
            System.out.print("Your choice: > ");
            choice = StateOfMenu.findByItemNumber(Integer.parseInt(scanner.next()));
        } else {
            System.out.println();
            for (StateOfMenu value : StateOfMenu.values()) {
                if (value.getItemNumber() / 10 == choice.getItemNumber()) {
                    System.out.println(value.getItemName());
                }
            }
            System.out.print("Your choice: > ");
            choice = StateOfMenu.findByItemNumber(Integer.parseInt(choice.getItemNumber() + scanner.next()));
        }
        return choice;
    }

    static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                if (element == 0) {
                    System.out.printf("%5.0f ", Math.abs(element));
                } else if (element > 0) {
                    System.out.printf("%5.2f ", element);
                } else {
                    System.out.printf("%4.2f ", element);
                }
            }
            System.out.println();
        }
    }
}

enum StateOfMenu {
    ADD_MATRICES(1, "1. Add matrices"),
    MULTIPLY_MATRIX_TO_A_CONSTANT(2, "2. Multiply matrix to a constant"),
    MULTIPLY_MATRICES(3, "3. Multiply matrices"),
    TRANSPOSE_MATRIX(4, "4. Transpose matrix"),
    TRANSP_MAIN_DIAGONAL(41, "1. Main diagonal"),
    TRANSP_SIDE_DIAGONAL(42, "2. Side diagonal"),
    TRANSP_VERTICAL_LINE(43, "3. Vertical line"),
    TRANSP_HORIZONTAL_LINE(44, "4. Horizontal line"),
    DETERMINANT(5, "5. Calculate a determinant"),
    INVERSE_MATRIX(6, "6. Inverse matrix"),
    EXIT(0, "0. Exit");

    private final int itemNumber;
    private final String itemName;

    StateOfMenu(int itemNumber, String itemName) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public static StateOfMenu findByItemNumber(int itemNumber) {
        for (StateOfMenu value: values()) {
            if (itemNumber == value.itemNumber) {
                return value;
            }
        }
        System.out.println("Your option is incorrect!");
        return null;
    }
}