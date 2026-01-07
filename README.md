# NTM: Space for Minecraft 1.7.10
# This HBM's NTM : Space fork was created for a multiplayer server Integrity : Space | Modern , changes that are made here consist of limitations and fun additions in gameplay and for memez.
## HBM's Nuclear Tech Mod + JameH2 Space for Minecraft 1.7.10

### Sound system limit
By default, the sound system only allows a limited amount of sounds to run at once (28 regular sounds and 4 streaming sounds), this causes issues when there's many machines running at once, since their looped sounds will constantly interrupt each other, causing them to immediately restart, which in some isolated cases has proven to cause massive lagspikes. To prevent this, NTM will increase the sound limit to 1000 regular sounds and 50 streaming sounds, this can be disabled with the config option `1.39_enableSoundExtension`.

# License
This software is licensed under the GNU Lesser General Public License version 3. In short: This software is free, you may run the software freely, create modified versions, distribute this software and distribute modified versions, as long as the modified software too has a free software license (with an exception for linking to this software, as stated by the "Lesser" part of the LGPL, where this may not be required). You win this round, Stallman. The full license can be found in the `LICENSE` and `LICENSE.LESSER` files.
