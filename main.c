#include <stdio.h>
#include <windows.h>
//#include <fileapi.h>

int main() {
/*
  HANDLE handle = CreateFile("mydir..", 0, 0, NULL, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, NULL);
  printf("%s\n", (handle != INVALID_HANDLE_VALUE) ? "true":"false");
*/
  WIN32_FIND_DATA FindFileData;
  HANDLE handle2 = FindFirstFile("mydir..\\*", &FindFileData);
  printf("%s\n", (handle2 != INVALID_HANDLE_VALUE) ? "true":"false");

  return 0;
}

