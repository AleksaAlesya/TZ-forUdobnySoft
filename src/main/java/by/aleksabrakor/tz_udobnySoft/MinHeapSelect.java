package by.aleksabrakor.tz_udobnySoft;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.PriorityQueue;

@Component
public class MinHeapSelect {

   public  Integer findNthMaxWithMinHeap(List<Integer> numbers, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n + 1);

        for (Integer number : numbers) {
            minHeap.offer(number);
            if (minHeap.size() > n) {
                minHeap.poll(); // Удаляем наименьший элемент, если размер кучи больше N
            }
        }
        return minHeap.peek();
    }
}
