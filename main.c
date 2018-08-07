#include <stdio.h>
#include <windows.h>
#include <tchar.h>
//#include <fileapi.h>

int main() {
  HANDLE handle = CreateFile("mydir..", 0, 0, NULL, OPEN_EXISTING, FILE_FLAG_BACKUP_SEMANTICS, NULL);
  printf("%s\n", (handle != INVALID_HANDLE_VALUE) ? "true":"false");

  LPTSTR lpFileName;
  TCHAR lpBuffer[MAX_PATH+1]; 
  GetFullPathName("mydir..\\xxx..", MAX_PATH+1, lpBuffer, &lpFileName); 
  printf("fullPathName：%s\n", lpBuffer);
  printf("fileName：%s\n", lpFileName);

  WIN32_FIND_DATA findFileData  ;
  HANDLE handle2 = FindFirstFile("mydir.\\xxx...", &findFileData);
  printf("%s\n", (handle2 != INVALID_HANDLE_VALUE) ? "true":"false");
  if(handle2 != INVALID_HANDLE_VALUE) {
    _tprintf("%s\n", findFileData.cFileName);
    FindClose(handle2);
  }

  return 0;
}

