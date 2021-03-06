@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if ((height == 0) || (width == 0)) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val width: Int, override val height: Int, e: E) : Matrix<E> {
    private val listOfCell = mutableListOf<E>()

    init {
        for (it in 0..height * width - 1) {
            listOfCell.add(e)
        }
    }

    override fun get(row: Int, column: Int): E = listOfCell[row * width + column]

    override fun get(cell: Cell): E = listOfCell[cell.row * width + cell.column]

    override fun set(row: Int, column: Int, value: E) {
        listOfCell[row * width + column] = value
    }

    override fun set(cell: Cell, value: E) {
        listOfCell[cell.row * width + cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        other as MatrixImpl<*>
        if (height != other.height) return false
        if (width != other.width) return false
        if (listOfCell != other.listOfCell) return false
        return true
    }

    override fun toString(): String {
        return "MatrixImpl(height=$height, width=$width, listOfCell=$listOfCell)"
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + listOfCell.hashCode()
        return result
    }
}

