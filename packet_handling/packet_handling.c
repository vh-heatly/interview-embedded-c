#include <stdint.h>
#include <stdio.h>

#define MAX_DATA_SIZE 256
static uint8_t data[MAX_DATA_SIZE];

// Parse the first packet from data_buf and return its size.
size_t parse_packet(const uint8_t* const data_buf, size_t buf_size);

// Forward the packet to someone that wants packets.
void forward_packet(const uint8_t* const packet, size_t packet_size);

void forward_packets(FILE* file_ptr)
{
  // Read up to MAX_DATA_SIZE bytes from file_ptr into data.
  ssize_t size = (ssize_t)fread(data, 1, MAX_DATA_SIZE, file_ptr);
  const uint8_t* packet_start_ptr = data;
  while (size > 0) {
    const ssize_t packet_length = parse_packet(packet_start_ptr, size);

    // Forward the packet somewhere, this is not important.
    forward_packet(packet_start_ptr, packet_length);

    size -= packet_length;
    packet_start_ptr += packet_length;
  }
}
