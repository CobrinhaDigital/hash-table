import java.util.LinkedList;

// O código abaixo usa dois tipos de estrutura de dados: listas encadeadas e hash tables
public class Main<K, V> {
    private final int tamanho; // Tamanho da tabela
    private final LinkedList<Entry<K, V>>[] table; //1 instância da da lista encadeada
    // Em java, os ponteiros usados pela LinkedList serão substituídos por chaves numéricas
    // associadas à strings e ambos ficarão armazenados dentro dela em pares

    // Construtor que inicializa a tabela de espalhamento com o tamanho dado
    public Main(int tamanho) {
        this.tamanho = tamanho;
        this.table = new LinkedList[tamanho];
    }

    // Classe interna para representar um par chave-valor
    private static class Entry<K, V> {
        private final K key; // Chave numérica
        private final V value; // String relacionada

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Função de espalhamento simples que calcula um índice com base na chave
    private int hash(K key) {
        int hashCode = key.hashCode(); // Método nativo de hash do java, gera chaves para mim
        return Math.abs(hashCode) % tamanho; //Math.abs evita gerar indexes negativos
    }

    // Insere um par chave-valor na tabela de espalhamento
    public void put(K key, V value) {
        int index = hash(key);
        if (table[index] == null) {
            // Se esse espaço estiver vazio, crie uma nova lista encadeada
            table[index] = new LinkedList<>();
        }
        // Adiciona o par chave-valor à lista daquele espaço
        table[index].add(new Entry<>(key, value));
    }

    // Recupera o valor associado a uma chave na tabela de espalhamento
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        if (list != null) {
            // Procura na lista do slot pelo par chave-valor correspondente à chave
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        // Retorna null se a chave não for encontrada
        return null;
    }

    // Remove um par chave-valor da tabela de espalhamento
    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> list = table[index];
        if (list != null) {
            // Procura na lista desse espaço pelo par chave-valor correspondente à chave e remove
            for (Entry<K, V> entry : list) {
                if (entry.key.equals(key)) {
                    list.remove(entry);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main<String, Integer> myTable = new Main<>(9);

        // Inserindo pares chave-valor na tabela
        myTable.put("Beatriz", 20); //String: Beatriz , chave: int 20
        myTable.put("Claudia", 5);
        myTable.put("Ana", 31);

        // Recupera valores usando as chaves
        System.out.println(myTable.get("Beatriz")); // Saída: 20
        System.out.println(myTable.get("Dario"));  // Saída: null (não existe)

        // Remove um par chave-valor
        myTable.remove("Claudia");

        // Tenta recuperar o valor removido
        System.out.println(myTable.get("Claudia"));   // Saída: null
    }
}
