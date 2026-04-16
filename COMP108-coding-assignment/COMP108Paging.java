//
// Coded by Prudence Wong 2021-12-29
// Updated 2023-02-26
// Updated 2024-03-03
// Updated 2025-03-04
// Updated 2026-03-07
//
// NOTE: You are allowed to add additional methods if you need.
//
// Name: Alex Tilev
// Student ID: 201915483
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// evictFIFO():
// O(n * p)
// For each of the n requests, we scan through all p cache entries to check for a hit: O(p).
// On a miss, we replace the element at the FIFO pointer in O(1), then advance the pointer.
// The pointer wraps around using modulo and never requires extra scanning.
// Total across all n requests: O(n * p).
//
// evictLFD():
// O(n^2 * p)
// For each of the n requests, we scan p cache entries to check for a hit: O(n * p) total.
// On a miss, for each of the p cache entries we scan up to n remaining requests
// to find the next occurrence of that page: O(p * n) per miss.
// In the worst case every request is a miss, giving O(n * p * n) = O(n^2 * p).
// Total: O(n^2 * p).
//

class COMP108Paging {

	// evictFIFO
	// Aim:
	// if a request is not in cache, evict the page present in cache for longest time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();

		// fifoPointer tracks which cache slot to evict next (circular)
		// Initially cArray[0] was added first so it gets evicted first
		int fifoPointer = 0;

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			// Check if request is already in cache
			for (int j = 0; j < cSize; j++) {
				if (cArray[j] == request) {
					hit = true;
					break;
				}
			}

			if (hit) {
				output.hitCount++;
				output.hitPattern += "h";
			} else {
				output.missCount++;
				output.hitPattern += "m";
				// Evict the page at fifoPointer (has been in cache longest)
				cArray[fifoPointer] = request;
				// Advance pointer, wrapping around to start of array when needed
				fifoPointer = (fifoPointer + 1) % cSize;
			}
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evictLFD
	// Aim:
	// if a request is not in cache, evict the number whose next request is the latest
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108PagingOutput
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108PagingOutput evictLFD(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108PagingOutput output = new COMP108PagingOutput();

		for (int i = 0; i < rSize; i++) {
			int request = rArray[i];
			boolean hit = false;

			// Check if request is already in cache
			for (int j = 0; j < cSize; j++) {
				if (cArray[j] == request) {
					hit = true;
					break;
				}
			}

			if (hit) {
				output.hitCount++;
				output.hitPattern += "h";
			} else {
				output.missCount++;
				output.hitPattern += "m";

				// For each cache slot, find the position of its next future request
				int[] nextPos = new int[cSize];
				for (int j = 0; j < cSize; j++) {
					// Look for cArray[j] in requests after position i
					nextPos[j] = findNextRequest(cArray[j], rArray, rSize, i + 1);
				}

				// Pick the cache slot whose next request is farthest away (largest index)
				// Ties are broken by leftmost position in cache (smaller j wins)
				// Since we only update evictIdx when strictly greater, the first (leftmost)
				// maximum stays, which is exactly what we want for tie-breaking
				int evictIdx = 0;
				for (int j = 1; j < cSize; j++) {
					if (nextPos[j] > nextPos[evictIdx]) {
						evictIdx = j;
					}
				}

				// Replace the evicted page with the requested page
				cArray[evictIdx] = request;
			}
		}

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// Helper: find the next occurrence of val in rArray starting at index 'start'
	// Returns rSize if val is not found (treated as infinity / never requested again)
	static int findNextRequest(int val, int[] rArray, int rSize, int start) {
		for (int i = start; i < rSize; i++) {
			if (rArray[i] == val) {
				return i;
			}
		}
		return rSize; // not found, treat as infinity
	}

	// DO NOT change this method
	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int size) {
		String outString="";
		
		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}

}