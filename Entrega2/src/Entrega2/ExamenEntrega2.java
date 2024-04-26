package Entrega2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;  


public abstract  class ExamenEntrega2 implements DataFrame   {
	

		public DataFrame emptyDataFrame(DataFrame df) {
		    List<String> columns = df.columNames();
		    List<String> emptyColumns = new ArrayList<>(columns);
		    Map<String, Integer> emptyIndex = new HashMap<>();
		    List<List<String>> emptyData = new ArrayList<>();
		    return DataFrameImpl.of(emptyColumns, emptyIndex, emptyData);
		}

	 
		public DataFrame addDataFrame(DataFrame df1, DataFrame df2) {
		    List<String> columns1 = df1.columNames();
		    List<String> columns2 = df2.columNames();
		    List<List<String>> data1 = df1.rows();
		    List<List<String>> data2 = df2.rows();

		    List<String> combinedColumns = new ArrayList<>(columns1);
		    combinedColumns.addAll(columns2);

		    Map<String, Integer> combinedIndex = new HashMap<>();
		    int index = 0;
		    for (String column : combinedColumns) {
		        combinedIndex.put(column, index++);
		    }
		   
		    List<List<String>> combinedData = new ArrayList<>(data1);
		    combinedData.addAll(data2);

		    return DataFrameImpl.of(combinedColumns, combinedIndex, combinedData);
		}

		public DataFrame removeColumnIndex(DataFrame df, int ci) {
		    if (ci < 0 || ci >= df.columNames().size()) {
		        throw new IllegalArgumentException("El índice está fuera del rango de columnas");
		    }
		    List<String> newColumns = new ArrayList<>(df.columNames());
		    newColumns.remove(ci);
		    Map<String, Integer> newIndex = new HashMap<>();
		    int index = 0;
		    for (int i = 0; i < df.columNames().size(); i++) {
		        if (i != ci) {
		            newIndex.put(newColumns.get(index), index);
		            index++;
		        }
		    }
		    List<List<String>> newData = new ArrayList<>();
		    for (List<String> row : df.rows()) {
		        List<String> newRow = new ArrayList<>(row);
		        newRow.remove(ci);
		        newData.add(newRow);
		    }
		    return DataFrameImpl.of(newColumns, newIndex, newData);
		}

	
	public List<DataFrame> divideDataFrame(DataFrame df, int ci) {
        // Verificar si el índice es válido
        if (ci < 0 || ci >= df.columNames().size()) {
            throw new IllegalArgumentException("El índice está fuera del rango de columnas");
        }
        List<String> columns = df.columNames();
        List<List<String>> data = df.rows();
        List<String> columns1 = new ArrayList<>(columns.subList(0, ci + 1));
        List<String> columns2 = new ArrayList<>(columns.subList(ci + 1, columns.size()));
        List<List<String>> data1 = new ArrayList<>();
        List<List<String>> data2 = new ArrayList<>();
        for (List<String> row : data) {
            List<String> row1 = new ArrayList<>(row.subList(0, ci + 1));
            List<String> row2 = new ArrayList<>(row.subList(ci + 1, row.size()));
            data1.add(row1);
            data2.add(row2);
        }
        DataFrame df1 = DataFrameImpl.of(columns1, data1);
        DataFrame df2 = DataFrameImpl.of(columns2, data2);

        List<DataFrame> result = new ArrayList<>();
        result.add(df1);
        result.add(df2);

        return result;
    }
	public static void main(String[] args) {
		DataFrame d = DataFrame.parse("src/Entrega2/personas.csv",
				 List.of("Id","Nombre","Apellidos","Altura","Fecha_Nacimiento"));
		DataFrame d2 = DataFrame.parse("src/Entrega2/mascotas.csv",
				 List.of("IdMascota","Nombre","Especie","Sexo","Ubicacion","Estado"));
		DataFrame d3 = DataFrame.parse("src/Entrega2/pp.csv",
				 List.of("Titulo", "Valoracion", "Anyo_estreno"));
		
		System.out.println("-----Ejercicio 1------");
		System.out.println(d.emptyDataFrame(d));
		System.out.println(d2.emptyDataFrame(d2));
		System.out.println(d3.emptyDataFrame(d3));
		
		System.out.println("-----Ejercicio 2------");
		System.out.println(d.addDataFrame(d, d3));
		System.out.println(d.addDataFrame(d2, d));
		
		System.out.println("-----Ejercicio 3------");
		System.out.println(d.removeColumnIndex(d, 2));
		System.out.println(d.removeColumnIndex(d, 3));
		System.out.println(d.removeColumnIndex(d, 4));
		// System.out.println(d.removeColumnIndex(d, 5)); Aquí saltaría error puesto que el indice esta fuera de las columnas
		
		System.out.println("-----Ejercicio 4------");
		List<DataFrame> DataFrames_separados = d.divideDataFrame(d, 2);
        System.out.println("DataFrame 1:");
        System.out.println(DataFrames_separados.get(0));

        System.out.println("DataFrame 2:");
        System.out.println(DataFrames_separados.get(1));
        
        List<DataFrame> DataFrames_separados2 = d.divideDataFrame(d2, 2);
        System.out.println("DataFrame 1:");
        System.out.println(DataFrames_separados2.get(0));

        System.out.println("DataFrame 2:");
        System.out.println(DataFrames_separados2.get(1));
       /*  ESTE DARÍA ERROR AL ESTAR EL ÍNDICE FUERA DE RANGO
        List<DataFrame> DataFrames_separados3 = d.divideDataFrame(d3, 3);
        System.out.println("DataFrame 1:");
        System.out.println(DataFrames_separados3.get(0));

        System.out.println("DataFrame 2:");
        System.out.println(DataFrames_separados3.get(1));
        */
}
}