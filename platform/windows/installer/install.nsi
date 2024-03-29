; Modified from the MCreator's NSI script (https://github.com/MCreator/MCreator/blob/master/platform/windows/installer/install.nsi)
; Big thanks to them

ManifestDPIAware true
Unicode true

!addplugindir "${NSIS_DIR}\Plugins\x86-unicode"
!addincludedir "${NSIS_DIR}\Include"

SetCompressor "lzma" ; to improve installer open performance and its size

!include "MUI2.nsh"

!define NAME "%name%"
!define VENDOR "%vendor%"
!define HOME_LINK "%home%"
!define ISSUE_LINK "%issue%"

!define VERSION "%version%"
!define BUILD "%branch%: %commit%"

!define PROG_DIR "%install_dir%"
!define USR_DIR "%user_dir%"
!define LAUNCH "%executable%"

!define BITS "64"

Name "${NAME}"
BrandingText "${NAME} ${VERSION} (${BUILD}) - Developed by ${VENDOR}"

!define MUI_PRODUCT "${NAME}"
!define MUI_ICON "..\..\src\main\resources\assets\img\icon.ico"
!define MUI_UNICON "..\..\src\main\resources\assets\img\icon.ico"

RequestExecutionLevel admin

VIAddVersionKey ProductName      "${NAME} ${VERSION} Installer"
VIAddVersionKey Comments         "Installer for ${NAME} ${VERSION}"
VIAddVersionKey CompanyName      "${VENDOR}"
VIAddVersionKey FileVersion      "${VERSION} (${BUILD})"
VIAddVersionKey LegalCopyright   "Copyright %year% (C) ${VENDOR}"
VIAddVersionKey FileDescription  "Installer for ${NAME} ${VERSION} (${BUILD})"
VIProductVersion                 "${VERSION}.0"

OutFile "${NAME}-v${VERSION}-Windows-x${BITS}.exe"

InstallDir "$PROGRAMFILES${BITS}\${PROG_DIR}"
!define INSTALLSIZE 306000

!define MUI_HEADERIMAGE
!define MUI_HEADERIMAGE_BITMAP "..\..\platform\windows\installer\installer.bmp"
!define MUI_WELCOMEFINISHPAGE_BITMAP "..\..\platform\windows\installer\installer_side.bmp"
!define MUI_UNWELCOMEFINISHPAGE_BITMAP "..\..\platform\windows\installer\installer_side.bmp"

!define MUI_FINISHPAGE_RUN_TEXT "Start ${NAME} after finish"
!define MUI_FINISHPAGE_RUN "$INSTDIR\${LAUNCH}"

!define MUI_FINISHPAGE_LINK "Issue?"
!define MUI_FINISHPAGE_LINK_LOCATION "${ISSUE_LINK}"

!define MUI_ABORTWARNING

!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE ".\win${BITS}\LICENSE"
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

!define MUI_PAGE_CUSTOMFUNCTION_SHOW un.ModifyUnConfirm
!define MUI_PAGE_CUSTOMFUNCTION_LEAVE un.ModifyUnConfirmLeave
!insertmacro MUI_UNPAGE_WELCOME
UninstPage Custom un.LockedListShow un.LockedListLeave
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH

!insertmacro MUI_LANGUAGE "English"

Function .onInit
ReadRegStr $0 HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "UninstallString"

${If} $0 != ""
${AndIf} ${Cmd} `MessageBox MB_YESNO|MB_ICONQUESTION "Installer has detected a previous version of ${NAME} installed. \
                 If you intend to install the new version in the same folder as the \
                 old version, you NEED to uninstall the old version first. \
                 Do you want to uninstall previous version?" /SD IDYES IDYES`
	Call UninstallPrevious
${EndIf}
FunctionEnd

Section "${NAME} ${VERSION}" Installation
  SectionIn RO

  ;Add files
  SetOutPath "$INSTDIR"

  File /r "win${BITS}\*"

  ;create desktop shortcut
  CreateShortCut "$DESKTOP\${NAME}.lnk" "$INSTDIR\${LAUNCH}"

  ;create start menu entry
  CreateShortCut "$SMPROGRAMS\${NAME}.lnk" "$INSTDIR\${LAUNCH}"

  ;write uninstall information to the registry
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "DisplayName" "${NAME} ${VERSION}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "Publisher" "${VENDOR}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "DisplayVersion" "${VERSION}.${BUILD}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "UninstallString" "$INSTDIR\uninstall.exe"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "DisplayIcon" "$INSTDIR\${LAUNCH}.exe"
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "EstimatedSize" ${INSTALLSIZE}
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}}" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "NoRepair" 1

  WriteUninstaller "$INSTDIR\uninstall.exe"

SectionEnd

Var keepUserData
Var keepUserDataState

Function un.ModifyUnConfirm
    ${NSD_CreateCheckbox} 120u -25u 70% 20u "Keep settings, data & cache (Unchecking this will remove everything)"
    Pop $keepUserData
    SetCtlColors $keepUserData "" ${MUI_BGCOLOR}

    ${IfThen} $keepUserDataState == "" ${|} StrCpy $keepUserDataState 1 ${|}
    ${NSD_SetState} $keepUserData $keepUserDataState
FunctionEnd

Function un.ModifyUnConfirmLeave
    ${NSD_GetState} $keepUserData $keepUserDataState
FunctionEnd

Section "Uninstall"
  ;Delete Folders of the launcher
  RMDir /r "$INSTDIR\jdk\*.*"
  RMDir /r "$INSTDIR\lib\*.*"
  RMDir /r "$INSTDIR\license\*.*"
  RMDir /r "$INSTDIR\plugins\*.*"

  ;Delete Files of the launcher
  Delete "$INSTDIR\${LAUNCH}"

  ;Remove uninstaller
  Delete "$INSTDIR\uninstall.exe"

  ;Remove the installation directory
  RMDir "$INSTDIR"

  ;Remove shortcut
  Delete "$DESKTOP\${NAME}.lnk"

  ;Remove start menu entry
  Delete "$SMPROGRAMS\${NAME}.lnk"

  ;Delete user data if preserve option was not selected
  ${If} $keepUserDataState <> 1
    RMDir /r "$PROFILE\.${USR_DIR}\*.*"
  ${EndIf}

  ;Delete Uninstaller And Unistall Registry Entries
  DeleteRegKey HKLM "Software\${MUI_PRODUCT}"
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}"
SectionEnd

Function UninstallPrevious
    ; Check for uninstaller.
    ReadRegStr $0 HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${MUI_PRODUCT}" "UninstallString"

    ${If} $0 != ""
        DetailPrint "Removing previous installation"

        Push $0
        Call GetParent
        Pop $1

        ; Run the uninstaller
        ExecWait '"$0" _?=$1'
    ${EndIf}
FunctionEnd

Function un.LockedListShow
  !insertmacro MUI_HEADER_TEXT 'Scanning for locked files' 'Clicking next will auto-close the programs listed below'
  LockedList::AddFile "$INSTDIR\jdk\bin\java.exe"
  LockedList::AddFile "$INSTDIR\jdk\bin\javaw.exe"
  LockedList::AddFolder "$INSTDIR\plugins"
  LockedList::AddFolder "$INSTDIR\lib"
  LockedList::Dialog /autonext /autoclosesilent
  Pop $R0
FunctionEnd

Function un.LockedListLeave
  StrCpy $R1 1
FunctionEnd

Function GetParent

  Exch $R0
  Push $R1
  Push $R2
  Push $R3

  StrCpy $R1 0
  StrLen $R2 $R0

  loop:
    IntOp $R1 $R1 + 1
    IntCmp $R1 $R2 get 0 get
    StrCpy $R3 $R0 1 -$R1
    StrCmp $R3 "\" get
  Goto loop

  get:
    StrCpy $R0 $R0 -$R1

    Pop $R3
    Pop $R2
    Pop $R1
    Exch $R0

FunctionEnd